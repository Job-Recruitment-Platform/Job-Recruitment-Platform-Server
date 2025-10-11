package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LogoutRequest {
    @NotBlank(message = "REFRESH_TOKEN_REQUIRED")
    private String refreshToken;
}
