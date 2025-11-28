package com.example.project;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Image extends Element {

    @Setter
    @Getter
    private String url;

    public Image() {}

    public Image(String url) {
        this.url = url;
    }

    @Override
    public void print() {
        System.out.println("Image with name: " + url);
    }
}
