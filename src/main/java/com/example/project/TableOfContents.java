package com.example.project;

public class TableOfContents implements Element {
    private final String something;

    public TableOfContents(String something) {
        this.something = something;
    }

    public void print() {
        System.out.println("Table of Contents: " + something);
    }
}
