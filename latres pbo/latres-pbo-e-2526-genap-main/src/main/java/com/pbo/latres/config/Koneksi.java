package com.pbo.latres.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
    try {

            String url = "jdbc:mysql://localhost:3306/latres_pbo";
            String user = "root";
            String password = "";

            koneksi = DriverManager.getConnection(url, user, password);

            System.out.println("Database terkoneksi!");

        } catch (SQLException e) {

            System.out.println("Koneksi gagal!");
            System.out.println(e.getMessage());

        }
    }
        return koneksi;
}
}