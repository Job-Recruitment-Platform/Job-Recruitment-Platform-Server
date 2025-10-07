package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.skill;

import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.CandidateSkill;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.skill.CandidateSkillResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CandidateSkillMapper {
    private final SkillMapper skillMapper;

    public CandidateSkillResponse toResponse(CandidateSkill candidateSkill) {
        return CandidateSkillResponse.builder()
                .skill(skillMapper.toResponse(candidateSkill.getSkill()))
                .level(candidateSkill.getLevel())
                .build();
    }
}
