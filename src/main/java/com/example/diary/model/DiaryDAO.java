package com.example.diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiaryDAO {
    String url = "jdbc:mysql://localhost:3306/diary";

    public void create(Diary diary) {

        String query = "INSERT INTO diary(title, text, user_id)";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getText());
            ps.setInt(3, diary.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
