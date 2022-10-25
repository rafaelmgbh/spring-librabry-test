package com.springlibrabryapi.librarycontrol.dto;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.util.UUID;



public class PapersDto {

    @NotBlank
    private String category;
    @NotBlank
    private String sumary;
    @NotBlank
    private String title;
    @NotBlank
    private String firstParagraph;

    private UUID author_id;


    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstParagraph() {
        return firstParagraph;
    }

    public void setFirstParagraph(String firstParagraph) {
        this.firstParagraph = firstParagraph;
    }

    public UUID getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(UUID author_id) {
        this.author_id = author_id;
    }
}
