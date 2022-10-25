package com.springlibrabryapi.librarycontrol.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDto {


    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 50)
    private String picture;



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
