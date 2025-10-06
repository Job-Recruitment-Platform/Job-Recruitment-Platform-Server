package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.account;

import java.time.OffsetDateTime;
import java.util.Set;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.SeniorityLevel;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.SkillLevelResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.location.LocationResponse;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse {
    private Long accountId;
    private String fullName;
    private LocationResponse location;
    private SeniorityLevel seniority;
    private Integer salaryExpectMin;
    private Integer salaryExpectMax;
    private String currency;
    private Boolean remotePref;
    private Boolean relocationPref;
    private Long avatarResourceId;
    private String bio;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;
    private Set<SkillLevelResponse> skills;
}
