package org.toanehihi.jobrecruitmentplatformserver.application.candidate.service;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateResponse;

public interface CandidateService {
    CandidateResponse updateProfile(Long accountId, CandidateRequest request);
}
