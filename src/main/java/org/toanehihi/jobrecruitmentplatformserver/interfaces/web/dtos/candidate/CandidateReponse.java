package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate;

import java.time.OffsetDateTime;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.Location;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.SeniorityLevel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateReponse {
    private Long id;
    private String fullName;
    private Location location;
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
}
