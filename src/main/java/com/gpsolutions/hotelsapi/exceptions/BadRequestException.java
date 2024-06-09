package com.gpsolutions.hotelsapi.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private String field;

    public BadRequestException(String field, String message) {
        super(message);
        this.field = field;
    }
}
