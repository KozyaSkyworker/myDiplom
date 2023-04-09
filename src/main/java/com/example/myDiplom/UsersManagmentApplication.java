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
            pw.println("\n1. Создать пользователя");
            // pw.println("\n2. Удаление пользователя");
            pw.println("\n2. Изменить пароль");
            pw.println("\n3. Изменить привилегии");
            pw.println("\n4. Просмотр пользователей");
            try {
                k = Integer.valueOf(br.readLine());
            }
            catch (Exception e) {
                pw.println("\nОшибка ввода");
            }
            switch (k)

            {
                case 1: create_user(cont); break;
                // case 2: drop_user(cont); break;
                case 2: change_pass(cont); break;
                case 3: change_priv(cont); break;
                case 4: view_user(cont); break;
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
            pw.println("Драйвер подключен");
        }
        catch (Exception e) {
            pw.println("Драйвер не подключен");
        }
        try {
            pw.println("Введите логин");
            name = br.readLine();
            pw.println("Введите пароль");
            pass = br.readLine();
            cnt = DriverManager.getConnection(url, name, pass);
            pw.println("Соединение установлено");
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
            pw.println("statm создан");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }

        try {
            pw.println("Введите логин");
            name = br.readLine();
            pw.println("Введите пароль");
            pass = br.readLine();

            statm.execute("CREATE USER '"+ name + "'@'localhost' identified by '" + pass + "'");
            pw.println("Пользователь создан");

            pw.println("Пользователь " + name + " с паролем " + pass + " успешно создан");
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
            pw.println("statm создан");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            strsql = "select user from mysql.user;";
            res = statm.executeQuery(strsql);
            pw.println("\n Список пользователей");
            while (res.next()) {
                pw.println(res.getString(1));
            }
        }
 catch (SQLException e) {
            pw.println("Ошибка show users");
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
            statm.execute("set password for '"+ name + "'@'localhost' = password('"+ pass + "');" );
            statm.execute("flush privileges");
            pw.println("Пароль изменен");
        }
        catch (SQLException e) {
            pw.println("Ошибка изменения пароля");
        }
    }

    public static void change_priv(Connection cn) {
        Statement statm = null;
        String strsql = "";
        ResultSet res;
        String name = "";
        String priv = "";
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
            pw.println("Введите новые привилегии \n" +
                    "1.SELECT - просмотр\n" +
                    "2.INSERT - добавление данных\n" +
                    "3.UPDATE - обновление данные\n" +
                    "4.DELETE - удаление данных\n" +
                    "5.ALL - все привилегии");
            priv = br.readLine();
        }
        catch (Exception e) {
            pw.println("Ошибка введения даных");
        }
        try {
            statm.execute("REVOKE ALL ON mydiplom.* FROM '" + name+"'@'localhost';");
            statm.execute("GRANT "+ priv + " on mydiplom.* TO '" + name+"'@'localhost';");
            pw.println("Привилегии изменены");
        }
        catch (SQLException e) {
            pw.println("Ошибка изменения привилегий");
        }
        try {
            statm.execute("use mysql");
            strsql = "select user,select_priv,insert_priv,update_priv,delete_priv from db where user=\"" + name +"\"";
            statm.execute("flush privileges");
            res = statm.executeQuery(strsql);
            pw.println("\n Список привилегий");
            pw.println("\nuser"+"\t|"+"Sel"+"\t|"+"Ins"+"\t|"+"Upd"+"\t|"+"Del");
            while (res.next()) {
                pw.println(res.getString(1)+"\t\t |" + res.getString(2)+"\t |" + res.getString(3)+"\t |"
                        + res.getString(4)+"\t |" + res.getString(5));
            }
        }
        catch (SQLException e) {
            pw.println("Ошибка показа привилегий");
        }
    }
}
