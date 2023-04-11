package com.example.myDiplom;

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

            statm.execute("insert into moderator (login, password, role, enabled) values ('" + name + "','"+ pass + "',default,default);");

            pw.println("Модератор " + name + " с паролем " + pass + " успешно создан");
        }
        catch (Exception e) {
            pw.println("Ошибка создания пользователя");
        }
        try {
            pw.println("Назначьте привилегии для созданного пользователя \n" +
                    "1.SELECT - просмотр\n" +
                    "2.INSERT - добавление данных\n" +
                    "3.UPDATE - обновление данные\n" +
                    "4.DELETE - удаление данных\n" +
                    "5.ALL - все привилегии");

            param = Integer.valueOf(br.readLine());

            switch (param){
                case 1: privs="select"; break;
                case 2: privs="insert"; break;
                case 3: privs="update"; break;
                case 4: privs="delete"; break;
                case 5: privs="all"; break;
            }

            statm.execute("GRANT "+ privs + " on mydiplom.* TO '" + name + "'@'localhost'");
            pw.println("Привилегии успешно заданы");
        }
        catch (SQLException | IOException e) {
            pw.println("Ошибка задания привилегий");
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
            statm.execute("set password for '"+ name + "'@'localhost' = password('"+ pass + "');" );
            pw.println("Пароль изменен");
        }
        catch (SQLException e) {
            pw.println("Ошибка изменения пароля");
        }
    }
}
