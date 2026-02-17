package com.eremkin.filmsspring.model;

import jakarta.persistence.*;
import java.util.Set;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название жанра обязательно")
    @Size(max = 255)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Film> films;

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
