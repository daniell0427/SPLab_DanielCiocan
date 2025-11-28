package com.example.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> element = new ArrayList<>();

    public Book() {}

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author a) {
        authors.add(a);
    }

    public void addContent(Section s) {
        element.add(s);
    }

    public void print() {
        System.out.println("Book: " + title);
        authors.forEach(Author::print);
        element.forEach(Section::print);
    }
}
