package com.example.myDiplom.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;


@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_term;

    @Column(columnDefinition = "varchar(200) not null")
    @NotBlank(message = "Поле не может быть пустым")
    private String name;

    @Column(columnDefinition = "TEXT not null")
    @NotBlank(message = "Поле не может быть пустым")
    private String description;

    private String first_date;
    private String newest_date;

    @Column(columnDefinition = "varchar(255) not null")
    @NotBlank(message = "Поле не может быть пустым")
    private String information_source;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTimestamp;
    @UpdateTimestamp
    private Timestamp updateTimestamp;

    public Term(){

    }

    public Term(String name, String description, String first_date, String newest_date, String information_source, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.name = name;
        this.description = description;
        this.first_date = first_date;
        this.newest_date = newest_date;
        this.information_source = information_source;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Term(Integer id_term, String name, String description, String first_date, String newest_date, String information_source, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.id_term = id_term;
        this.name = name;
        this.description = description;
        this.first_date = first_date;
        this.newest_date = newest_date;
        this.information_source = information_source;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Integer getId_term() {
        return id_term;
    }

    public void setId_term(Integer id_term) {
        this.id_term = id_term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirst_date() {
        return first_date;
    }

    public void setFirst_date(String first_date) {
        this.first_date = first_date;
    }

    public String getNewest_date() {
        return newest_date;
    }

    public void setNewest_date(String newest_date) {
        this.newest_date = newest_date;
    }

    public String getInformation_source() {
        return information_source;
    }

    public void setInformation_source(String information_source) {
        this.information_source = information_source;
    }

    public Timestamp getCreateTimestamp() { return createTimestamp; }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getUpdateTimestamp() { return updateTimestamp; }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.createTimestamp = updateTimestamp;
    }

    @Override
    public String toString() {
        return "Term{" +
                "id_term=" + id_term +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", first_date='" + first_date + '\'' +
                ", newest_date='" + newest_date + '\'' +
                ", information_source='" + information_source + '\'' +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", updateTimestamp='" + updateTimestamp + '\'' +
                '}';
    }
}
