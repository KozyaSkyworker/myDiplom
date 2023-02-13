package com.example.myDiplom.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_author;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 3, max = 35)
    private String fullname;

    private String img;
    private String birthday_date;
    private String main_activity;

    public Author(){

    }

    public Author(String fullname, String img, String birthday_date, String main_activity){
        this.fullname = fullname;
        this.img = img;
        this.birthday_date = birthday_date;
        this.main_activity = main_activity;
    }

    public Author(Integer id_author, String fullname, String img, String birthday_date, String main_activity) {
        this.id_author = id_author;
        this.fullname = fullname;
        this.img = img;
        this.birthday_date = birthday_date;
        this.main_activity = main_activity;
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

    public String getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(String birthday_date) {
        this.birthday_date = birthday_date;
    }

    public String getMain_activity() {
        return main_activity;
    }

    public void setMain_activity(String main_activity) {
        this.main_activity = main_activity;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", fullname='" + fullname + '\'' +
                ", img='" + img + '\'' +
                ", birthday_date='" + birthday_date + '\'' +
                ", main_activity='" + main_activity + '\'' +
                '}';
    }
}
