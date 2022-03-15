package com.example.diary.controller;

import com.example.diary.MainApplication;
import com.example.diary.model.Diary;
import com.example.diary.model.DiaryDAO;
import com.example.diary.model.UserDAO;
import com.example.diary.model.UserSingleton;
import com.example.diary.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DiaryController implements Initializable {
    UserDAO userDAO = new UserDAO();
    DiaryDAO diaryDAO = new DiaryDAO();

    @FXML
    private TextField id;
    @FXML
    private TextField title;
    @FXML
    private TextField text;
    @FXML
    private Label status;
    @FXML
    private Label userStatus;

    @FXML
    public void onCreateButton(ActionEvent event) throws IOException {

        String title1 = title.getText();
        String text1 = text.getText();

        if (!Validation.isValidTitle(title1)) {
            status.setText("Incorrect title.");
        } else if (text1.isEmpty()) {
            status.setText("Text field is empty.");
        } else {
            int userId = userDAO.searchIdByEmail(userStatus.getText());

            Diary diary = new Diary(title1, text1, userId);
            diaryDAO.create(diary);

            status.setText("Record has been added successfully.");
        }
    }

    @FXML
    public void onUpdateButton(ActionEvent event) throws IOException {
        String id1 = id.getText();
        String title1 = title.getText();
        String text1 = text.getText();

        if (!Validation.isValidId(id1)) {
            status.setText("Incorrect id.");
        } else if (!Validation.isValidTitle(title1)) {
            status.setText("Incorrect title.");
        } else if (text1.isEmpty()) {
            status.setText("Text field is empty.");
        } else {
            int id2 = Integer.parseInt(id1);

            Diary diary = new Diary(id2, title1, text1);
            diaryDAO.update(diary);
            status.setText("Record has been updated successfully.");
        }
    }

    @FXML
    public void onDeleteButton(ActionEvent event) throws IOException {

    }

    @FXML
    public void onSearchButton(ActionEvent event) throws IOException {
        String title1 = title.getText();
        List<Diary> list = diaryDAO.searchAllObjectOrByTitle(title1);
    }


    @FXML
    public void onEnter(ActionEvent event) throws IOException {
        onCreateButton(event);
    }

    public void onLogOutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root, 280, 180));
        loginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email = UserSingleton.getInstance().getEmail();
        userStatus.setText(email);

    }
}
