package com.example.project.books.async;

import com.example.project.books.command.Command;

public class AsyncRequest<R> {

    private final String id;
    private final Command<R> command;

    private volatile AsyncStatus status = AsyncStatus.PENDING;
    private volatile R result;
    private volatile Throwable error;

    public AsyncRequest(String id, Command<R> command) {
        this.id = id;
        this.command = command;
    }

    public String getId() {
        return id;
    }

    public Command<R> getCommand() {
        return command;
    }

    public AsyncStatus getStatus() {
        return status;
    }

    public void setStatus(AsyncStatus status) {
        this.status = status;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}

