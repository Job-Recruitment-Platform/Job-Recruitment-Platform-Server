package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.skill;

import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Skill;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.skill.SkillResponse;

@Component
public class SkillMapper {
    public SkillResponse toResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .aliases(skill.getAliases())
                .dateCreated(skill.getDateCreated())
                .build();
    }
}
