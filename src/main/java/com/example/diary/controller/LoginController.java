package com.example.diary.controller;

import com.example.diary.MainApplication;
import com.example.diary.model.UserDAO;
import com.example.diary.utils.BCryptPassword;
import com.example.diary.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    UserDAO userDAO = new UserDAO();

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label status;

    @FXML
    public void onSignInButton(ActionEvent event) throws IOException {
        String mail = email.getText();
        String pass = password.getText();
        if (Validation.isValidEmail(mail) && Validation.isValidPassword(pass)) {
            String passwordDB = userDAO.getBCryptPassword(mail);
            if(passwordDB.equals("")){
                status.setText("Incorrect email or password.");
            } else {
                boolean isValidPassword = BCryptPassword.checkPassword(pass, passwordDB);
                if (isValidPassword) {
                    status.setText("Joined succesfully.");
                } else {
                    status.setText("Incorrect email or password.");
                }
            }
        } else {
            status.setText("Incorrect data.");
        }
    }

}