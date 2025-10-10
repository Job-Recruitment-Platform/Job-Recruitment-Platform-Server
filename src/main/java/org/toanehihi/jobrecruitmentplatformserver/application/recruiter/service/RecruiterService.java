package org.toanehihi.jobrecruitmentplatformserver.application.recruiter.service;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company.CompanyRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company.CompanyResponse;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.recruiter.RecruiterRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.recruiter.RecruiterResponse;

public interface RecruiterService {
    RecruiterResponse getProfile();

    RecruiterResponse updateProfile(RecruiterRequest request);

    CompanyResponse updateCompany(CompanyRequest request);
}
