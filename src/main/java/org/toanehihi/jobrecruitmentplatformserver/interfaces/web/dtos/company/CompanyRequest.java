package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company;

import java.util.HashSet;
import java.util.Set;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.CompanyLocation;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Recruiter;

import lombok.Getter;

@Getter
public class CompanyRequest {
    private String name;
    private String website;
    private String size;
    private Long logoResourceId;
    private Recruiter recruiter;
    private Set<CompanyLocation> companyLocations = new HashSet<>();
}
