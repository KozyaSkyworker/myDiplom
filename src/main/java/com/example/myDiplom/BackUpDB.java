package com.example.myDiplom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BackUpDB {
    static PrintWriter pw = new PrintWriter(System.out, true);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args){
        try {

            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "cd C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin && mysqldump -uroot -p mydiplom > D:\\fme\\MYDB.sql");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
