package com.example.project;

import java.util.Arrays;

public class Author {
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
