package com.example.project;

public interface Element {
    void print();
    default void add(Element e) {}
    default void remove(Element e) {}
    default Element get(int i) { return null; }
}
