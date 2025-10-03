package org.toanehihi.jobrecruitmentplatformserver.application.job.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.job.JobMapper;
import org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories.JobRepository;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.PageResult;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.job.JobResponse;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public JobResponse getJobById(Long id) {
        return jobMapper.toResponse(jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found")));
    }

    @Override
    public PageResult<JobResponse> getAllJobs(int page, int size, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<JobResponse> jobs = jobRepository.findAll(pageable)
                .map(jobMapper::toResponse);


        return PageResult.from(jobs);
    }
}
