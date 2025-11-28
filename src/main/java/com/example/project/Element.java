package com.example.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Element() {}

    public Integer getId() {
        return id;
    }

    public abstract void print();

    public void add(Element e) {}
    public void remove(Element e) {}
    public Element get(int i) { return null; }
}
