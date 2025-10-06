package org.toanehihi.jobrecruitmentplatformserver.application.account.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.toanehihi.jobrecruitmentplatformserver.application.email.EmailService;
import org.toanehihi.jobrecruitmentplatformserver.application.token.service.TokenService;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.AppException;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.ErrorCode;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Account;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.AccountStatus;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.AccountRepository;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.ForgotPasswordRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.ResendVerificationRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.ResetPasswordRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final String RESET_TOKEN_PREFIX = "reset_token:";
    private static final String VERIFICATION_TOKEN_PREFIX = "verify_token:";

    @Override
    public void changeAccountStatus(Long accountId, AccountStatus status) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        account.setStatus(status);
        accountRepository.save(account);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        String resetToken = UUID.randomUUID().toString();
        tokenService.addToken(RESET_TOKEN_PREFIX, resetToken, account.getEmail());
        emailService.sendPasswordResetEmail(account.getEmail(), resetToken);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        String redisKey = RESET_TOKEN_PREFIX + request.getToken();
        String redisValue = tokenService.getValue(redisKey);

        if (redisValue == null) {
            throw new AppException(ErrorCode.ACCOUNT_RESET_TOKEN_INVALID);
        }

        Account account = accountRepository.findByEmail(redisValue)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (passwordEncoder.matches(request.getNewPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.ACCOUNT_PASSWORD_SAME_AS_OLD);
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);
        tokenService.deleteValue(redisKey);
    }

    @Override
    public void resendVerificationEmail(ResendVerificationRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (account.getVerifiedAt() != null) {
            throw new AppException(ErrorCode.ACCOUNT_ALREADY_VERIFIED);
        }

        String verificationToken = UUID.randomUUID().toString();
        tokenService.addToken(VERIFICATION_TOKEN_PREFIX, verificationToken, account.getEmail());

        emailService.sendVerificationEmail(account.getEmail(), verificationToken);

        log.info("Verification email resent to: {}", account.getEmail());
    }

    @Override
    public void verifyEmail(String token) {
        String redisKey = VERIFICATION_TOKEN_PREFIX + token;
        String email = tokenService.getValue(redisKey);

        if (email == null) {
            throw new AppException(ErrorCode.ACCOUNT_VERIFY_TOKEN_INVALID);
        }

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        account.setVerifiedAt(OffsetDateTime.now());
        accountRepository.save(account);

        tokenService.deleteValue(redisKey);

        log.info("Email verified successfully for: {}", email);
    }
}
