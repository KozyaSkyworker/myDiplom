package com.example.myDiplom.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;


@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private String date;
    @Column(columnDefinition = "date default(CURRENT_DATE)")
    private String timestamp;

    public Term(){

    }

    public Term(String name, String description, String first_date, String newest_date, String information_source, String date, String timestamp) {
        this.name = name;
        this.description = description;
        this.first_date = first_date;
        this.newest_date = newest_date;
        this.information_source = information_source;
        this.date = date;
        this.timestamp = timestamp;
    }

    public Term(Integer id_term, String name, String description, String first_date, String newest_date, String information_source, String date, String timestamp) {
        this.id_term = id_term;
        this.name = name;
        this.description = description;
        this.first_date = first_date;
        this.newest_date = newest_date;
        this.information_source = information_source;
        this.date = date;
        this.timestamp = timestamp;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimestamp() {return timestamp;}

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
                ", date='" + date + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
