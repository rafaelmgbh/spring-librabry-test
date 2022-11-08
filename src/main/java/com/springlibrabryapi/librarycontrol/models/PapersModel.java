package com.springlibrabryapi.librarycontrol.models;




import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "papers")
public class PapersModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String category;
    @Column(nullable = false, length = 50)
    private String sumary;
    @Column
    private String title;
    @Column(nullable = false, length = 50)
    private String firstParagraph;

    @Column(nullable = false)
    private UUID author_id;


    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorsModel author;

    public PapersModel() {

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

    public String getTitle() {
        return title;
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

    public AuthorsModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorsModel author) {
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}
