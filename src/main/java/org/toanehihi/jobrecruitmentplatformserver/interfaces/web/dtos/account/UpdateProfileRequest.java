package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account;

import java.util.Set;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.SeniorityLevel;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.SkillLevelRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.location.LocationRequest;

import lombok.Getter;

@Getter
public class UpdateProfileRequest {
    private String fullName;
    private LocationRequest location;
    private SeniorityLevel seniority;
    private Integer salaryExpectMin;
    private Integer salaryExpectMax;
    private String currency;
    private Boolean remotePref;
    private Boolean relocationPref;
    private Long avatarResourceId;
    private String bio;
    private Set<SkillLevelRequest> skills;
}
