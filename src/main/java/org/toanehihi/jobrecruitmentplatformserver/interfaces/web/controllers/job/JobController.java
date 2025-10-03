package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.controllers.job;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.toanehihi.jobrecruitmentplatformserver.application.job.service.JobService;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.PageResult;
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

}
