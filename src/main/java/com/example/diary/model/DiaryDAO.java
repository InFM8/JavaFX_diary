package com.example.diary.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
    String url = "jdbc:mysql://localhost:3306/diary";

    public void create(Diary diary) {

        String query = "INSERT INTO diary(title, text, user_id) VALUES(?,?,?)";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getText());
            ps.setInt(3, diary.getId());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Diary> searchAllObjectOrByTitle(String title) {

        String query = "";
        if (title.isEmpty()) {
            query = "SELECT * FROM diary";
        } else {
            query = "SELECT * FROM diary WHERE title LIKE '%" + title + "%'";
        }
        ArrayList<Diary> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Diary(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getInt("user_id")
                ));
            }
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
