package com.example.myDiplom.entities;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

//import javax.validation.constraints.NotEmpty;

@Entity
@IdClass(AuthorTermPKId.class)
public class AuthorTerm {

    @Id
//    @NotNull(message = "Поле не может быть пустым")
    private Integer id_term;
    @Id
//    @NotNull(message = "Поле не может быть пустым")
    private Integer id_author;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Поле не может быть пустым")
    private String author_vklad;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTimestamp;
    @UpdateTimestamp
    private Timestamp updateTimestamp;

    public AuthorTerm (){

    }

    public AuthorTerm(Integer id_term, Integer id_author, String author_vklad, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.id_term = id_term;
        this.id_author = id_author;
        this.author_vklad = author_vklad;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Integer getId_term() {
        return id_term;
    }

    public void setId_term(Integer id_term) {
        this.id_term = id_term;
    }

    public Integer getId_author() {
        return id_author;
    }

    public void setId_author(Integer id_author) {
        this.id_author = id_author;
    }

    public String getAuthor_vklad() {
        return author_vklad;
    }

    public void setAuthor_vklad(String author_vklad) {
        this.author_vklad = author_vklad;
    }

    public Timestamp getCreateTimestamp() { return createTimestamp; }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getUpdateTimestamp() { return updateTimestamp; }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.createTimestamp = updateTimestamp;
    }
}
