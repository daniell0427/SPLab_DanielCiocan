package com.example.project;

public class Table implements Element {
    private final String something;

    public Table(String something) {
        this.something = something;
    }

    public void print() {
        System.out.println(something);
    }
}
