package com.example.project.books.command;

import com.example.project.Book;
import com.example.project.persistence.CrudRepository;

public class UpdateBookCommand implements Command {

    private final CrudRepository<Book, Integer> repo;
    private final int id;
    private final String title;

    public UpdateBookCommand(CrudRepository<Book, Integer> repo, int id, String title) {
        this.repo = repo;
        this.id = id;
        this.title = title;
    }

    @Override
    public Object execute() {
        Book existing = repo.findById(id);
        if (existing == null) {
            return "Book not found";
        }

        existing.setTitle(title);
        existing.setId(id);
        return repo.save(existing);
    }
}
