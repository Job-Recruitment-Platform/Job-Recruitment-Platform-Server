package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.company;

import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.location.LocationRequest;

import lombok.Getter;

@Getter
public class CompanyLocationRequest {
    private LocationRequest location;
    private Boolean isHeadquarter;
}
