package com.seoridam.rehearserver.global.common;

import lombok.Builder;

public class FailResponse {

    private StatusEnum status;
    private String errorMessage;

    @Builder
    public FailResponse(StatusEnum status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
