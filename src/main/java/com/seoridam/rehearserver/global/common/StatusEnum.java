package com.seoridam.rehearserver.global.common;

public enum StatusEnum {


    OK(200, "OK"),
    CREATED(201, "CREATED"),
    ACCEPTED(202, "ACCEPTED"),
    NO_CONTENT(204, "NO_CONTENT"),

    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

    private int statusCode;
    private String status;

    StatusEnum(int statusCode, String status) {
        this.statusCode = statusCode;
        this.status = status;
    }
}
