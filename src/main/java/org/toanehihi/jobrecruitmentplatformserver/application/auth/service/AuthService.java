package org.toanehihi.jobrecruitmentplatformserver.application.auth.service;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.AccountResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.CandidateAccountRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.RecruiterAccountRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.AuthenticationResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.GoogleLoginRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.LoginRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.LogoutRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.RefreshTokenRequest;

public interface AuthService {
    AccountResponse candidateRegister(CandidateAccountRequest request);

    AccountResponse recruiterRegister(RecruiterAccountRequest request);

    AuthenticationResponse login(LoginRequest request);

    AuthenticationResponse loginWithGoogle(GoogleLoginRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    void logout(LogoutRequest request);
}
