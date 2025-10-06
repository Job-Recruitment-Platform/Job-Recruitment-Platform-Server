package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.location;

import lombok.Getter;

@Getter
public class LocationRequest {
    private String streetAddress;
    private String ward;
    private String district;
    private String provinceCity;
    private String country;
}
