package com.example.project.books.command.executors;

import com.example.project.books.command.Command;
import org.springframework.stereotype.Service;

@Service
public class SyncCommandExecutor implements CommandExecutor {

    @Override
    public <R> R execute(Command<R> command) {
        return command.execute();
    }
}
