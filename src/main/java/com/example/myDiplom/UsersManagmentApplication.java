package com.example.myDiplom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;

public class UsersManagmentApplication {
    static PrintWriter pw = new PrintWriter(System.out, true);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        int k = 0;
        Connection cont = conn();
        do {
            pw.println("\n0. Выход");
            pw.println("1. Создать модератора");
            pw.println("2. Посмотреть логины модераторов");
            pw.println("3. Изменить пароль модератора");
            try {
                k = Integer.valueOf(br.readLine());
            }
            catch (Exception e) {
                pw.println("\nОшибка ввода");
            }
            switch (k)

            {
                case 1: create_user(cont); break;
                case 2: view_user(cont); break;
                case 3: change_pass(cont); break;
            }
        } while (k != 0);
    }
    public static Connection conn() {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String name = "";
        String pass = "";
        Connection cnt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            pw.println("=== Драйвер подключен ===");
        }
        catch (Exception e) {
            pw.println("Драйвер не подключен");
        }
        try {
            pw.println("Введите логин:");
            name = br.readLine();
            pw.println("Введите пароль:");
            pass = br.readLine();
            cnt = DriverManager.getConnection(url, name, pass);
            pw.println("=== Соединение установлено ===");
        }
        catch (SQLException e) {
            pw.println("Ошибка соединения");
        }
        catch (Exception e) {
            pw.println("Ошибка ввода имени или пароля");
        }
        return cnt;
    }
    public static void create_user(Connection cn) {
        Statement statm = null;
        String strsql = "";
        String name = "";
        String pass = "";
        Integer param = 0;
        String privs = "";
        try {
            statm = cn.createStatement();
            pw.println("=== statm создан === ");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            statm.execute("use mydiplom");
            pw.println("===== База используется =====");
        }
        catch (SQLException ev){
            pw.println("Ошибка выбора базы mydiplom");
            pw.println(ev);
        }

        try {
            pw.println("Введите логин нового модератора:");
            name = br.readLine();
            pw.println("Введите пароль:");
            pass = br.readLine();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(pass);

            statm.execute("insert into moderator (login, password, role, enabled) values ('" + name + "','"+ encodedPassword + "',default,default);");

            pw.println("Модератор " + name + " с паролем " + pass + " успешно создан");
        }
        catch (Exception e) {
            pw.println("Ошибка создания пользователя");
        }

    }
    public static void view_user(Connection cn) {
        ResultSet res;
        Statement statm = null;
        String strsql = "";
        try {
            statm = cn.createStatement();
            pw.println("=== statm создан ===");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            statm.execute("use mydiplom");
            pw.println("===== База используется =====");
        }
        catch (SQLException ev){
            pw.println("Ошибка выбора базы mydiplom");
            pw.println(ev);
        }
        try {
            strsql = "select login from moderator;";
            res = statm.executeQuery(strsql);
            pw.println("\n = Список модераторов (их логинов) ресурса:");
            while (res.next()) {
                pw.println(res.getString(1));
            }
        }
 catch (SQLException e) {
            pw.println("Ошибка выборки модераторов");
        }
    }
    public static void change_pass(Connection cn) {
        Statement statm = null;
        String strsql = "";
        String name = "";
        String pass = "";
        String newEncodedPassword = "";
        try {
            statm = cn.createStatement();
            pw.println("statm создан");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            pw.println("Введите логин");
            name = br.readLine();
            pw.println("Введите новый пароль");
            pass = br.readLine();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            newEncodedPassword = encoder.encode(pass);
        }
        catch (Exception e) {
            pw.println("Ошибка введения даных");
        }
        try {
            statm.execute("use mydiplom");
            pw.println("===== База используется =====");
        }
        catch (SQLException ev){
            pw.println("Ошибка выбора базы mydiplom");
            pw.println(ev);
        }
        try {
            statm.execute("UPDATE moderator SET password = '" + newEncodedPassword +  "' WHERE login = '" + name + "';" );
            pw.println("Пароль изменен");
        }
        catch (SQLException e) {
            pw.println("Ошибка изменения пароля");
        }
    }
}
