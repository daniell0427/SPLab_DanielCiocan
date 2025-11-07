package com.example.project;

public class AlignRight implements AlignStrategy {
    public void render(Paragraph paragraph) {
        System.out.println("        " + paragraph.getText());
    }
}
