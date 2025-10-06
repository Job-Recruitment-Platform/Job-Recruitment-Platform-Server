package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GoogleLoginRequest {
    @NotBlank(message = "ID token is required")
    private String idToken;
}
