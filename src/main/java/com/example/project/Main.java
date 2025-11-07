package com.example.project;

import java.util.*;

interface Element {
    void print();
    default void add(Element e) {}
    default void remove(Element e) {}
    default Element get(int i) { return null; }
}

class Author {
    private final String name;
    private final String surname;

    public Author(String name) {
        String[] parts = name.split(" ");

        this.name = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 1));
        this.surname = parts[parts.length - 1];
    }

    public void print() {
        System.out.println("Author: " + name + " " + surname);
    }
}

class Book implements Element {
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

class Section implements Element {
    private final String title;
    private final List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public void add(Element e) {
        children.add(e);
    }

    public void print() {
        System.out.println(title);
        for (Element e : children)
            e.print();
    }
}

class Paragraph implements Element {
    private final String text;

    public Paragraph(String text) {
        this.text = text;
    }

    public void print() {
        System.out.println("Paragraph: " + text);
    }
}

class Image implements Element {
    private final String url;

    public Image(String url) {
        this.url = url;
    }

    public void print() {
        System.out.println("Image with name: " + url);
    }
}

class Table implements Element {
    private final String something;

    public Table(String something) {
        this.something = something;
    }

    public void print() {
        System.out.println(something);
    }
}

class TableOfContents implements Element {
    private final String something;

    public TableOfContents(String something) {
        this.something = something;
    }

    public void print() {
        System.out.println("Table of Contents: " + something);
    }
}

public class Main {
    public static void main(String[] args) {
        Book noapteBuna = new Book("Noapte buna, copii!");
        Author rpGheo = new Author("Radu Pavel Gheo");
        noapteBuna.addAuthor(rpGheo);

        Section cap1 = new Section("Capitolul 1");
        Section cap11 = new Section("Capitolul 1.1");
        Section cap111 = new Section("Capitolul 1.1.1");
        Section cap1111 = new Section("Subchapter 1.1.1.1");

        noapteBuna.addContent(new Paragraph("Multumesc celor care ..."));
        noapteBuna.addContent(cap1);
        cap1.add(new Paragraph("Moto capitol"));
        cap1.add(cap11);
        cap11.add(new Paragraph("Text from subchapter 1.1"));
        cap11.add(cap111);
        cap111.add(new Paragraph("Text from subchapter 1.1.1"));
        cap111.add(cap1111);
        cap1111.add(new Image("Image subchapter 1.1.1.1"));

        noapteBuna.print();
    }
}
