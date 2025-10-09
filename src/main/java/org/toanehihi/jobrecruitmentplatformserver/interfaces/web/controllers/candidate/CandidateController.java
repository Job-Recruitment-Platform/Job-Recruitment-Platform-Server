package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.controllers.candidate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.toanehihi.jobrecruitmentplatformserver.application.candidate.service.CandidateService;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.DataResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.candidate.CandidateResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping("/profile")
    DataResponse<CandidateResponse> getProfile() {
        return DataResponse.<CandidateResponse>builder()
                .data(candidateService.getProfile())
                .build();
    }

    @PutMapping("/profile/{accountId}")
    DataResponse<CandidateResponse> updateCandidateProfile(@PathVariable Long accountId,
            @RequestBody CandidateRequest request) {
        return DataResponse.<CandidateResponse>builder()
                .data(candidateService.updateCandidateProfile(accountId, request))
                .build();
    }
}
