package com.rentease.database;
import java.sql.*;

public class DatabaseConnection {

    
    private static final String URL = "jdbc:mysql://localhost:3306/db_rentease2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    public static Connection getConnection() {
//        Perubahan Dari Sini
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Koneksi database berhasil!");
                System.out.println("Database Offline");
            } catch (SQLException e) {               
                final String URL2 = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6634208";
                final String USERNAME2 = "sql6634208";
                final String PASSWORD2 = "wmnKT757ms";
                try {
                    connection = DriverManager.getConnection(URL2, USERNAME2, PASSWORD2);
                    System.out.println("Database Online");
                } catch(SQLException e2) {
                    System.out.println("Koneksi database gagal: " + e2.getMessage());
                }        
            }
        }
        return connection;
    }
//          Sampai Sini
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Tutup koneksi database berhasil!");
            } catch (SQLException e) {
                System.out.println("Tutup koneksi database gagal: " + e.getMessage());
            }
        }
    }
}
