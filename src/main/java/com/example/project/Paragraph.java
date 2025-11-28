package com.example.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Paragraph extends Element {

    @Getter
    private String text;

    @Setter
    @Transient
    private AlignStrategy alignStrategy;

    public Paragraph() {}

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        if (alignStrategy != null)
            alignStrategy.render(this);
        else
            System.out.println("Paragraph: " + text);
    }
}
