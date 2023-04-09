package com.example.myDiplom.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_author;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 3, max = 35)
    @Column(columnDefinition = "varchar(200) not null")
    private String fullname;

    private String img;
    private String birth_date;
    private String main_activity;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTimestamp;
    @UpdateTimestamp
    private Timestamp updateTimestamp;

    public Author(){

    }

    public Author(String fullname, String img, String birth_date, String main_activity, Timestamp createTimestamp, Timestamp updateTimestamp){
        this.fullname = fullname;
        this.img = img;
        this.birth_date = birth_date;
        this.main_activity = main_activity;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Author(Integer id_author, String fullname, String img, String birth_date, String main_activity, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.id_author = id_author;
        this.fullname = fullname;
        this.img = img;
        this.birth_date = birth_date;
        this.main_activity = main_activity;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Integer getId_author() {
        return id_author;
    }

    public void setId_author(Integer id_author) {
        this.id_author = id_author;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getMain_activity() {
        return main_activity;
    }

    public void setMain_activity(String main_activity) {
        this.main_activity = main_activity;
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
        return "Author{" +
                "id_author=" + id_author +
                ", fullname='" + fullname + '\'' +
                ", img='" + img + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", main_activity='" + main_activity + '\'' +
                ", createDate='" + createTimestamp + '\'' +
                ", updateDate='" + updateTimestamp + '\'' +
                '}';
    }
}
