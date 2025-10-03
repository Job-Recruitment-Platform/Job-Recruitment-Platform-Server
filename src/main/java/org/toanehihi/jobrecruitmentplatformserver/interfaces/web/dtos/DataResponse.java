package org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos;

import lombok.Builder;

@Builder
public class DataResponse<T> {
    private String message;
    private int code;
    private T data;

}
