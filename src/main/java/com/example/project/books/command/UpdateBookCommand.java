package com.example.project.books.command;

import com.example.project.books.service.BooksService;

public class UpdateBookCommand implements Command<String> {

    private final BooksService booksService;
    private final int id;
    private final String title;

    public UpdateBookCommand(BooksService service, int id, String title) {
        this.booksService = service;
        this.id = id;
        this.title = title;
    }

    @Override
    public String execute() {
        return booksService.updateBook(id, title);
    }
}

