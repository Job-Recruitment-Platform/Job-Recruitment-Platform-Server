package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.account;

import java.time.OffsetDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Account;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.AccountStatus;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.AuthProvider;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.AccountResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.CandidateAccountRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.RecruiterAccountRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final PasswordEncoder passwordEncoder;

    public Account toCandidateAccount(CandidateAccountRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(AccountStatus.ACTIVE)
                .provider(AuthProvider.LOCAL)
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now())
                .build();
    }

    public Account toRecruiterAccount(RecruiterAccountRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(AccountStatus.ACTIVE)
                .provider(AuthProvider.LOCAL)
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now())
                .build();
    }

    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .roleName(account.getRole().getName())
                .status(account.getStatus())
                .provider(account.getProvider())
                .verifiedAt(account.getVerifiedAt())
                .dateCreated(account.getDateCreated())
                .build();
    }
}
