package com.llhtdqc.spring_boot_3.Response;

public class ApiResponse<T> {
    private T data;
    private int statusCode;
    private String message;
    private boolean success;

    public ApiResponse(){

    }

    public ApiResponse(T data, int statusCode, String message, boolean success) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
