package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.mappers.company;

import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Company;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company.CompanyRequest;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company.CompanyResponse;

@Component
public class CompanyMapper {
    public void updateCompany(Company company, CompanyRequest request) {
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setSize(request.getWebsite());
    }

    public CompanyResponse toResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .website(company.getWebsite())
                .size(company.getSize())
                .logoResourceId(company.getLogoResourceId())
                .verified(company.isVerified())
                .build();
    }
}
