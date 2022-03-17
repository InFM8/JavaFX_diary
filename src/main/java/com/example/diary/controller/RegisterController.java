package com.example.diary.controller;

import com.example.diary.MainApplication;
import com.example.diary.model.User;
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

public class RegisterController {
    UserDAO userDAO = new UserDAO();

    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private Label status;

    @FXML
    public void regRegisterButton(ActionEvent event) throws IOException {
        String email1 = email.getText();
        String pass1 = pass.getText();
        String confirmPass1 = confirmPass.getText();

        if (Validation.isValidEmail(email1) && Validation.isValidPassword(pass1) && Validation.isValidPassword(confirmPass1)) {
            status.setText("Registered successfully.");
            String passBCrypt = BCryptPassword.hashPassword(pass1);

            User user = new User(email1, passBCrypt);
            userDAO.insert(user);

            goToLogin(event);
        }

        if (!Validation.isValidPassword(pass1)) status.setText("Incorrect password.");
        if (!Validation.isValidPassword(confirmPass1)) status.setText("Incorrect confirm password.");
        if (!Validation.isValidEmail(email1)) status.setText("Incorrect email.");
    }

    @FXML
    public void onEnter(ActionEvent event) throws IOException {
        regRegisterButton(event);
    }

    @FXML
    public void regBackButton(ActionEvent event) throws IOException {
        goToLogin(event);
    }

    @FXML
    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root, 280, 180));
        loginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
