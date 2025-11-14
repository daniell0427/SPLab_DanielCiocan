package com.example.project.books.command;

public interface Command<R> {
    R execute();
}

