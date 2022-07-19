package com.example.diary.controller;

import com.example.diary.MainApplication;
import com.example.diary.model.Diary;
import com.example.diary.model.DiaryDAO;
import com.example.diary.model.UserDAO;
import com.example.diary.model.UserSingleton;
import com.example.diary.utils.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    //Table view ------------
    @FXML
    private TableView table;
    @FXML
    private TableColumn tableId;
    @FXML
    private TableColumn tableTitle;
    @FXML
    private TableColumn tableText;
    @FXML
    private TableColumn tableUserId;
    ObservableList<Diary> list = FXCollections.observableArrayList();

    @FXML
    public void onSearchButton() {
        list.clear();
        String title1 = title.getText();

        List<Diary> ls = diaryDAO.searchAllObjectOrByTitle(title1);

        for (Diary diary : ls) {
            list.add(new Diary(diary.getId(), diary.getTitle(), diary.getText(), diary.getUser_id()));

            tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            tableText.setCellValueFactory(new PropertyValueFactory<>("text"));
            tableUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));

            table.setItems(list);
//            table.getItems();
        }
        if (ls.isEmpty()) {
            status.setText("You don't have any listing yet.");
        } else {
            status.setText("Found successfully.");
        }
    }
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
        String id1 = id.getText();

        if (!Validation.isValidId(id1)) {
            status.setText("Enter a valid id.");
        } else {
            Integer id2 = Integer.parseInt(id1);
            if(id2.equals("" ) || id2==null) {
                status.setText("Listing with this id is not exist.");
            }
            diaryDAO.deleteByID(id2);
            status.setText("Record deleted.");
        }
    }
    @FXML
    public void onEnterCreate(ActionEvent event) throws IOException {
        onCreateButton(event);
    }

    @FXML
    public void onEnterSearch() {
        onSearchButton();
    }

    @FXML
    public void onIdDelete(ActionEvent event) throws IOException {
        onDeleteButton(event);
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
