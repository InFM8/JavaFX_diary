package com.example.diary.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
    UserDAO userDAO = new UserDAO();
    String url = "jdbc:mysql://localhost:3306/diary";

    public void create(Diary diary) {

        String query = "INSERT INTO diary(title, text, user_id) VALUES(?,?,?)";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);


            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getText());

            ps.setInt(3, diary.getUser_id());


            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Diary> searchAllObjectOrByTitle(String title) {
        String query = "";

        int userID = userDAO.searchIdByEmail(UserSingleton.getInstance().getEmail());

        if (title.isEmpty()) {
            query = "SELECT * FROM diary WHERE user_id = " + userID;
        } else {
            query = "SELECT * FROM diary WHERE title LIKE '%" + title + "%' AND user_id = "+userID;
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
                        rs.getInt("user_id")));
            }

            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Diary diary) {
        String query = "UPDATE diary SET title=?, text=? WHERE id = ?";

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

    public void deleteByID(int id) {
        String query = "DELETE FROM diary WHERE id = ?";

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  For test
     */
    public Diary searchByTitle(String title) {
        String query = "SELECT * FROM diary WHERE title = ?";
        ArrayList<Diary> list = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
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
        try {
            return list.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
