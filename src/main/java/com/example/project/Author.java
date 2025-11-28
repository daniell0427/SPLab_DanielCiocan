package com.example.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String surname;

    public Author() {}

    public Author(String fullName) {
        String[] parts = fullName.split(" ");
        this.name = String.join(" ", java.util.Arrays.copyOfRange(parts, 0, parts.length - 1));
        this.surname = parts[parts.length - 1];
    }

    public void print() {
        System.out.println(name + " " + surname);
    }
}
