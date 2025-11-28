package com.example.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Section extends Element {

    @Setter
    @Getter
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Element> children = new ArrayList<>();

    public Section() {}

    public Section(String title) {
        this.title = title;
    }

    @Override
    public void add(Element e) {
        children.add(e);
    }

    @Override
    public void remove(Element e) {
        children.remove(e);
    }

    @Override
    public Element get(int i) {
        return children.get(i);
    }

    @Override
    public void print() {
        System.out.println("Section: " + title);
        for (Element e : children)
            e.print();
    }
}
