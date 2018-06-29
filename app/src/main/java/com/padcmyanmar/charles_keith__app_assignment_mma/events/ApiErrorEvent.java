package com.padcmyanmar.charles_keith__app_assignment_mma.events;

public class ApiErrorEvent {

    private String errorMsg;

    public ApiErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
