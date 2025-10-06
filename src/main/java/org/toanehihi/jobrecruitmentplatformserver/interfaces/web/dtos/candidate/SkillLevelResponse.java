package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.Skill;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillLevelResponse {
    private Skill skill;
    private Integer level;
}
