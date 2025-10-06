package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
