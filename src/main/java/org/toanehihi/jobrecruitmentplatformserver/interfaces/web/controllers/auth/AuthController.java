package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.controllers.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.toanehihi.jobrecruitmentplatformserver.application.auth.service.AuthService;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.DataResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.AccountResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account.CreateAccountRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.AuthenticationResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.GoogleLoginRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.LoginRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.LogoutRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth.RefreshTokenRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public DataResponse<AccountResponse> register(@Valid @RequestBody CreateAccountRequest request) {
        return DataResponse.<AccountResponse>builder()
                .data(authService.registerWithCredentials(request))
                .build();
    }

    @PostMapping("/login")
    public DataResponse<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return DataResponse.<AuthenticationResponse>builder()
                .data(authService.login(request))
                .build();
    }

    @PostMapping("/login/google")
    public DataResponse<AuthenticationResponse> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        return DataResponse.<AuthenticationResponse>builder()
                .data(authService.loginWithGoogle(request))
                .build();
    }

    @PostMapping("/refresh")
    public DataResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return DataResponse.<AuthenticationResponse>builder()
                .data(authService.refreshToken(request))
                .build();
    }

    @PostMapping("/logout")
    public DataResponse<String> refreshToken(@RequestBody LogoutRequest request) {
        authService.logout(request);
        return DataResponse.<String>builder()
                .data("Logout successfully")
                .build();
    }
}
