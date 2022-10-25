package com.springlibrabryapi.librarycontrol.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author")
public class AuthorsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String picture;

    public AuthorsModel() {

    }

    public AuthorsModel(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
