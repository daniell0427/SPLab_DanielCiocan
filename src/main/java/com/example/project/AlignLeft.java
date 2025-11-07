package com.example.project;

public class AlignLeft implements AlignStrategy {
    public void render(Paragraph paragraph) {
        System.out.println(paragraph.getText());
    }
}
