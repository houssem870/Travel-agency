package com.ditraacademy.travel_agency.utils;

public class ErrorResponseModel {
    private String message;

    public ErrorResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
