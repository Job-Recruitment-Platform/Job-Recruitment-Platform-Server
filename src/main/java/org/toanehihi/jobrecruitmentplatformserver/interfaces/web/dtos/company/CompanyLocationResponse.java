package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.location.LocationResponse;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyLocationResponse {
    private LocationResponse location;
    private Boolean isHeadquarter;
}
