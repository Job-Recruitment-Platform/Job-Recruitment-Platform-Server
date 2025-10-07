package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.skill;

import lombok.Getter;

@Getter
public class CandidateSkillRequest {
    private String skillName;
    private Integer level;
}
