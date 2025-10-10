package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account;

import lombok.Getter;

@Getter
public class RecruiterAccountRequest {
    private String fullName;
    private String email;
    private String password;
    private String companyName;
}
