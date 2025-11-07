package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
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
