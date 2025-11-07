package com.example.project;

public class Image implements Element {
    private final String url;

    public Image(String url) {
        this.url = url;
    }

    public void print() {
        System.out.println("Image with name: " + url);
    }
}
