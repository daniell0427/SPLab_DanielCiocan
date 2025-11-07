package com.example.project;

public class AlignCenter implements AlignStrategy {
    public void render(Paragraph paragraph) {
        System.out.println("    " + paragraph.getText());
    }
}
