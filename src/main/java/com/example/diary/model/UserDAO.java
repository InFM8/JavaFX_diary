package com.example.diary.model;

import java.sql.*;

public class UserDAO {

    String url = "jdbc:mysql://localhost:3306/diary";

    public void insert(User user) {
        String query = "INSERT INTO users(email,password) VALUES (?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            ps.executeUpdate();
            ps.close();
            con.close();

            System.out.println("created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("created unsuccessfully");
        }
    }

    public String getBCryptPassword(String username) {
        String query = "SELECT password FROM users WHERE email = ?";

        String passwordBCrypted = "";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                passwordBCrypted = rs.getString("password");
            }
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordBCrypted;
    }

    public int searchIdByEmail(String email) {
        String query = "";
        query = "SELECT id FROM users WHERE email = ?";
        int email2 = 0;

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                email2 = rs.getInt("id");
            }
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email2;
    }

}
