package com.example.project.books.command;

import com.example.project.Book;
import com.example.project.persistence.CrudRepository;

public class AddBookCommand implements Command {

    private final CrudRepository<Book, Integer> repo;
    private final String title;

    public AddBookCommand(CrudRepository<Book, Integer> repo, String title) {
        this.repo = repo;
        this.title = title;
    }

    @Override
    public Object execute() {
        Book book = new Book(title);
        return repo.save(book);
    }
}
