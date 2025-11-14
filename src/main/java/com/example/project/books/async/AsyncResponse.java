package com.example.project.books.async;


public class AsyncResponse {

    private String requestId;
    private AsyncStatus status;
    private Object result;
    private String error;

    public AsyncResponse(String requestId, AsyncStatus status, Object result, String error) {
        this.requestId = requestId;
        this.status = status;
        this.result = result;
        this.error = error;
    }

    public String getRequestId() {
        return requestId;
    }

    public AsyncStatus getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}

