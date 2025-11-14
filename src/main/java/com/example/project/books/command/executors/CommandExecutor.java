package com.example.project.books.command.executors;

import com.example.project.books.command.Command;

public interface CommandExecutor {
    <R> R execute(Command<R> command);
}

