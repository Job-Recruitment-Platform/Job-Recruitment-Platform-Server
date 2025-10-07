package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.candidate;

import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Candidate;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.location.LocationMapper;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CandidateMapper {
    private final LocationMapper locationMapper;
    public void updateCandidate(Candidate candidate, CandidateRequest request) {
        candidate.setFullName(request.getFullName());
        candidate.setSeniority(request.getSeniority());
        candidate.setSalaryExpectMin(request.getSalaryExpectMin());
        candidate.setSalaryExpectMax(request.getSalaryExpectMax());
        candidate.setCurrency(request.getCurrency());
        candidate.setRemotePref(request.getRemotePref());
        candidate.setRelocationPref(request.getRelocationPref());
        candidate.setBio(request.getBio());
    }

    public CandidateResponse toResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .accountId(candidate.getAccount().getId())
                .fullName(candidate.getFullName())
                .location(locationMapper.toResponse(candidate.getLocation()))
                .seniority(candidate.getSeniority())
                .salaryExpectMin(candidate.getSalaryExpectMin())
                .salaryExpectMax(candidate.getSalaryExpectMax())
                .currency(candidate.getCurrency())
                .remotePref(candidate.getRemotePref())
                .relocationPref(candidate.getRelocationPref())
                .avatarResourceId(candidate.getAvatarResourceId())
                .bio(candidate.getBio())
                .dateCreated(candidate.getDateCreated())
                .dateUpdated(candidate.getDateUpdated())
                .build();
    }
}
