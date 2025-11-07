package com.example.project;

import lombok.Setter;

public class Paragraph implements Element {
    private final String text;
    @Setter
    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
    }

    String getText() {
        return text;
    }

    public void print() {
        if (alignStrategy != null)
            alignStrategy.render(this);
        else
            System.out.println("Paragraph: " + text);
    }
}
