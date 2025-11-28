package com.example.project.books.command;

import com.example.project.Book;
import com.example.project.persistence.CrudRepository;

public class DeleteBookCommand implements Command {

    private final CrudRepository<Book, Integer> repo;
    private final int id;

    public DeleteBookCommand(CrudRepository<Book, Integer> repo, int id) {
        this.repo = repo;
        this.id = id;
    }

    @Override
    public Object execute() {
        if (!repo.existsById(id)) {
            return "Book not found";
        }

        repo.deleteById(id);
        return "Book deleted: " + id;
    }
}
