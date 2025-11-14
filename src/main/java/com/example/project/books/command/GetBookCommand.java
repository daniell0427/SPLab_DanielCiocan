package com.example.project.books.command;

import com.example.project.books.service.BooksService;

public class GetBookCommand implements Command<String> {

    private final BooksService booksService;
    private final int id;

    public GetBookCommand(BooksService service, int id) {
        this.booksService = service;
        this.id = id;
    }

    @Override
    public String execute() {
        return booksService.getById(id);
    }
}

