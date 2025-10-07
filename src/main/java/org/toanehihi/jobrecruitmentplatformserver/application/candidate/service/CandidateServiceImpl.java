package org.toanehihi.jobrecruitmentplatformserver.application.candidate.service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.AppException;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.ErrorCode;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Candidate;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.CandidateSkill;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Location;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Skill;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.candidate.CandidateMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.location.LocationMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.skill.CandidateSkillMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.CandidateRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.LocationRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.SkillRepository;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.skill.CandidateSkillRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final LocationRepository locationRepository;
    private final SkillRepository skillRepository;
    private final LocationMapper locationMapper;
    private final CandidateMapper candidateMapper;
    private final CandidateSkillMapper candidateSkillMapper;

    @Override
    @Transactional
    public CandidateResponse updateProfile(Long accountId, CandidateRequest request) {
        Candidate candidate = candidateRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_CANDIDATE_NOT_FOUND));

        // Update location
        if (candidate.getLocation() != null) {
            Optional<Location> locationOpt = locationRepository.findById(candidate.getLocation().getId());
            if (locationOpt.isPresent()) {
                Location existLoc = locationOpt.get();
                locationMapper.updateLocation(existLoc, request.getLocation());
                locationRepository.save(existLoc);
            }
        } else {
            Location location = locationMapper.toLocation(request.getLocation());
            candidate.setLocation(location);
            locationRepository.save(location);
        }

        // Update skill
        candidate.getSkills().clear();
        Set<CandidateSkill> updatedSkills = new HashSet<>();
        for (CandidateSkillRequest skillRequest : request.getSkills()) {
            Skill skill = skillRepository.findByName(skillRequest.getSkillName())
                    .orElseGet(() -> skillRepository.save(Skill.builder()
                            .name(skillRequest.getSkillName())
                            .dateCreated(OffsetDateTime.now())
                            .build()));

            CandidateSkill candidateSkill = CandidateSkill.builder()
                    .candidate(candidate)
                    .skill(skill)
                    .build();
            updatedSkills.add(candidateSkill);
        }

        candidate.getSkills().addAll(updatedSkills);
        candidateRepository.save(candidate);

        // Update others
        candidateMapper.updateCandidate(candidate, request);
        candidate.setDateUpdated(OffsetDateTime.now());
        Candidate savedCandidate = candidateRepository.save(candidate);
        CandidateResponse response = candidateMapper.toResponse(savedCandidate);
        response.setSkills(updatedSkills.stream().map(candidateSkillMapper::toResponse).collect(Collectors.toSet()));
        return response;
    }

}
