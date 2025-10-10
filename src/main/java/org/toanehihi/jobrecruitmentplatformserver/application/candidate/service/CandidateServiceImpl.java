package org.toanehihi.jobrecruitmentplatformserver.application.candidate.service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.AppException;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.ErrorCode;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Account;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Candidate;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.CandidateSkill;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Job;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Location;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.SavedJob;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Skill;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.candidate.CandidateMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.job.SavedJobMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.location.LocationMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.CandidateRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.JobRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.LocationRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.SavedJobRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.SkillRepository;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.security.CurrentAccountProvider;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.job.SavedJobResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.skill.CandidateSkillRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final LocationRepository locationRepository;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final SavedJobRepository savedJobRepository;
    private final LocationMapper locationMapper;
    private final CandidateMapper candidateMapper;
    private final SavedJobMapper savedJobMapper;
    private final CurrentAccountProvider currentAccountProvider;

    @Override
    public CandidateResponse getProfile() {
        return candidateMapper.toResponse(getCurrentCandidate());
    }

    @Override
    @Transactional
    public CandidateResponse updateProfile(CandidateRequest request) {
        Candidate candidate = getCurrentCandidate();

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
                    .level(skillRequest.getLevel())
                    .build();
            updatedSkills.add(candidateSkill);
        }
        candidate.getSkills().clear();
        candidate.getSkills().addAll(updatedSkills);

        // Update others
        candidateMapper.updateCandidate(candidate, request);
        candidate.setDateUpdated(OffsetDateTime.now());
        Candidate savedCandidate = candidateRepository.save(candidate);

        return candidateMapper.toResponse(savedCandidate);
    }

    @Override
    public SavedJobResponse saveJob(Long jobId) {
        Candidate candidate = getCurrentCandidate();
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_FOUND));
        SavedJob savedJob = SavedJob.builder()
                .candidate(candidate)
                .job(job)
                .savedAt(OffsetDateTime.now())
                .build();
        SavedJob result = savedJobRepository.save(savedJob);
        return savedJobMapper.toResponse(result);
    }

    @Override
    public void removeSavedJob(Long jobId) {
        Candidate candidate = getCurrentCandidate();
        savedJobRepository.deleteByCandidateAndJobId(candidate, jobId);
    }

    // Private methods
    private Candidate getCurrentCandidate() {
        Account account = currentAccountProvider.getCurrentAccount();
        return candidateRepository.findById(account.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

}
