package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Book implements Element {
    private final String title;
    private final List<Author> authors = new ArrayList<>();
    private final List<Element> contents = new ArrayList<>();

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author a) {
        authors.add(a);
    }

    public void addContent(Element e) {
        contents.add(e);
    }

    public void print() {
        System.out.println("Book: " + title + '\n');
        System.out.println("Authors:");
        for (Author a : authors)
            a.print();
        System.out.println("\n");
        for (Element e : contents)
            e.print();
    }
}
