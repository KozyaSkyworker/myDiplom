package com.example.myDiplom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;

public class CreateDbApplication {
    static PrintWriter pw = new PrintWriter(System.out, true);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        int k = 0;
        Connection cont = conn();
        do {
            pw.println("\n0. Выход");
            pw.println("1. Создать БД");
            pw.println("2. Проверить существование БД");
            pw.println("3. Удалить БД");
            try {
                k = Integer.valueOf(br.readLine());
            }
            catch (Exception e) {
                pw.println("\nОшибка ввода");
            }
            switch (k)
            {
                case 1: create_db(cont); break;
                case 2: view_db(cont); break;
                case 3: drop_db(cont); break;
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
            pw.println("===== Драйвер подключен =====");
        }
        catch (Exception e) {
            pw.println("Драйвер не подключен...");
        }
        try {
            pw.println("Введите логин: ");
            name = br.readLine();
            pw.println("Введите пароль: ");
            pass = br.readLine();
            cnt = DriverManager.getConnection(url, name, pass);
            pw.println("\n===== Соединение установлено =====");
        }
        catch (SQLException e) {
            pw.println("Ошибка соединения...");
            pw.println(e);
        }
        catch (Exception e) {
            pw.println("Ошибка ввода имени или пароля...");
        }
        return cnt;
    }
    // =============================================================================== FUNCTION create_db =====
    public static void create_db(Connection cn) {
        Statement statm = null;
        String strsql = "";
        // ======================================== STATM create ========================================
        try {
            statm = cn.createStatement();
            pw.println("\n===== statm создан =====");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        // ======================================== CREATE DB ===========================================
        try {
            statm.execute("create database mydiplom");
            pw.println("\nБаза данных mydiplom создана");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания БД");
        }
    }
    // ================================================================================ FUNCTION view_db =====
    public static void view_db(Connection cn) {
        ResultSet res;
        Statement statm = null;
        String strsql = "";
        try {
            statm = cn.createStatement();
            pw.println("\n===== statm создан =====");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            strsql = "show databases";
            res = statm.executeQuery(strsql);
            while (res.next()) {
                pw.println(res.getString(1));
            }
        }
        catch (SQLException e) {
            pw.println("Ошибка show databases...");
        }
    }
    // =============================================================================== FUNCTION drop_db =====
    public static void drop_db(Connection cn) {
        Statement statm = null;
        try {
            statm = cn.createStatement();
            pw.println("\n===== statm создан =====");
        }
        catch (SQLException e) {
            pw.println("Ошибка создания statm");
        }
        try {
            statm.execute("drop database mydiplom");
            pw.println("\nБаза данных mydiplom удалена");
        }
        catch (SQLException e) {
            pw.println("Ошибка удаления БД");
        }
    }
}
