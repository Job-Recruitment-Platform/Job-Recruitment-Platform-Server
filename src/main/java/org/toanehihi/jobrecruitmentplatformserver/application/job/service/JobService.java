package org.toanehihi.jobrecruitmentplatformserver.application.job.service;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.job.JobResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.PageResult;

public interface JobService {
    JobResponse getJobById(Long id);
    PageResult<JobResponse> getAllJobs(int page, int size, String sortBy, String sortDir);
}
