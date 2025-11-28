package com.example.project.books.command;

import com.example.project.Book;
import com.example.project.persistence.CrudRepository;

public class GetBookCommand implements Command {

    private final CrudRepository<Book, Integer> repo;
    private final int id;

    public GetBookCommand(CrudRepository<Book, Integer> repo, int id) {
        this.repo = repo;
        this.id = id;
    }

    @Override
    public Object execute() {
        return repo.findById(id);
    }
}
