package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ForgotPasswordRequest {
    @NotBlank(message = "AUTH_EMAIL_BLANK")
    @Email(message = "AUTH_INVALID_EMAIL")
    private String email;
}
