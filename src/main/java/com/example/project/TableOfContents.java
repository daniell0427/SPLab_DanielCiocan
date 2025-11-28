package com.example.project;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TableOfContents extends Element {

    @Setter
    @Getter
    private String something;

    public TableOfContents() {}

    public TableOfContents(String something) {
        this.something = something;
    }

    @Override
    public void print() {
        System.out.println("Table of Contents: " + something);
    }
}
