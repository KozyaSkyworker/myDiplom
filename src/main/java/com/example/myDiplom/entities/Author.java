package com.example.myDiplom.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_author;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 3, max = 35)
    @Column(columnDefinition = "varchar(200) not null")
    private String fullname;

    private String birth_date;
    private String born_country;
    private String born_city;
    private String nationality;
    private String img;
    private String main_activity;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTimestamp;
    @UpdateTimestamp
    private Timestamp updateTimestamp;

    public Author(){

    }

    public Author(String fullname, String birth_date, String born_country, String born_city, String nationality, String img, String main_activity, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.fullname = fullname;
        this.birth_date = birth_date;
        this.born_country = born_country;
        this.born_city = born_city;
        this.nationality = nationality;
        this.img = img;
        this.main_activity = main_activity;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Author(Integer id_author, String fullname, String birth_date, String born_country, String born_city, String nationality, String img, String main_activity, Timestamp createTimestamp, Timestamp updateTimestamp) {
        this.id_author = id_author;
        this.fullname = fullname;
        this.birth_date = birth_date;
        this.born_country = born_country;
        this.born_city = born_city;
        this.nationality = nationality;
        this.img = img;
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

    public String getBorn_country() {
        return born_country;
    }

    public void setBorn_country(String born_country) {
        this.born_country = born_country;
    }

    public String getBorn_city() {
        return born_city;
    }

    public void setBorn_city(String born_city) {
        this.born_city = born_city;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", fullname='" + fullname + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", born_country='" + born_country + '\'' +
                ", born_city='" + born_city + '\'' +
                ", nationality='" + nationality + '\'' +
                ", img='" + img + '\'' +
                ", main_activity='" + main_activity + '\'' +
                ", createTimestamp=" + createTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }
}
