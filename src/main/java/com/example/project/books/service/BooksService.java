package com.example.project.books.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BooksService {

    private final Map<Integer, String> books = new HashMap<>();

    public synchronized List<String> getAll() {
        return new ArrayList<>(books.values());
    }

    public synchronized String getById(int id) {
        return books.getOrDefault(id, "Book not found");
    }

    public synchronized String addBook(String title) {
        int id = books.size() + 1;
        books.put(id, title);
        return "Added book " + id + ": " + title;
    }

    public synchronized String updateBook(int id, String title) {
        books.put(id, title);
        return "Updated book " + id + " to: " + title;
    }

    public synchronized String deleteBook(int id) {
        return books.remove(id) != null
                ? "Deleted book " + id
                : "Book not found";
    }
}
