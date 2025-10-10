package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company;

import java.time.OffsetDateTime;
import java.util.Set;

import org.toanehihi.jobrecruitmentplatformserver.domain.model.Recruiter;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponse {
    private Long id;
    private String name;
    private String website;
    private String size;
    private Long logoResourceId;
    private boolean verified;
    private OffsetDateTime dateCreated;
    private Recruiter recruiter;
    private Set<CompanyLocationResponse> companyLocations;
}
