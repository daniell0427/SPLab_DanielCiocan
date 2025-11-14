package com.example.project.books.command;

import com.example.project.books.service.BooksService;

public class AddBookCommand implements Command<String> {

    private final BooksService booksService;
    private final String title;

    public AddBookCommand(BooksService service, String title) {
        this.booksService = service;
        this.title = title;
    }

    @Override
    public String execute() {
        return booksService.addBook(title);
    }
}

