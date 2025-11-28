package com.example.project.books.command;

import com.example.project.Book;
import com.example.project.persistence.CrudRepository;
import java.util.List;

public class GetAllBooksCommand implements Command {

    private final CrudRepository<Book, Integer> repo;

    public GetAllBooksCommand(CrudRepository<Book, Integer> repo) {
        this.repo = repo;
    }

    @Override
    public Object execute() {
        return repo.findAll();
    }
}
