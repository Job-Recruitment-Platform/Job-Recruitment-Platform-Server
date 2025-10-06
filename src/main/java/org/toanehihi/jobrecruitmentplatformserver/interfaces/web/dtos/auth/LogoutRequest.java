package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.auth;

import lombok.Getter;

@Getter
public class LogoutRequest {
    private String refreshToken;
}
