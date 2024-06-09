package com.gpsolutions.hotelsapi.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private String field;

    public NotFoundException(String field, String message) {
        super(message);
        this.field = field;
    }
}
