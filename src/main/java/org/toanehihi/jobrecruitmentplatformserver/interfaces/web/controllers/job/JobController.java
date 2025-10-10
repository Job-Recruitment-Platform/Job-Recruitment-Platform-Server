package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.controllers.job;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.toanehihi.jobrecruitmentplatformserver.application.job.service.JobService;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Account;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.annotation.CurrentUser;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.DataResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.PageResult;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.job.CreateJobRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.job.JobResponse;

@RestController
@RequestMapping("/api/v1/jobs")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public PageResult<JobResponse> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return jobService.getAllJobs(page, size, sortBy, sortDir);
    }

    @PostMapping
    public DataResponse<JobResponse> createJob(@CurrentUser Account account, @RequestBody CreateJobRequest request) {
        return DataResponse.<JobResponse>builder()
                .data(jobService.createJob(account, request))
                .build();
    }

}
