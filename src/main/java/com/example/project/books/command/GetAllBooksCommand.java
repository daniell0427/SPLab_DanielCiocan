package com.example.project.books.command;

import com.example.project.books.service.BooksService;

import java.util.List;

public class GetAllBooksCommand implements Command<List<String>> {

    private final BooksService booksService;

    public GetAllBooksCommand(BooksService service) {
        this.booksService = service;
    }

    @Override
    public List<String> execute() {
        return booksService.getAll();
    }
}
