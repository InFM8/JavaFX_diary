package com.example.diary.controller;

import com.example.diary.model.Diary;
import com.example.diary.model.DiaryDAO;
import com.example.diary.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class DiaryController {
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
    public void onCreateButton(ActionEvent event) throws IOException {
        String title1 = title.getText();
        String text1 = text.getText();

        if (!Validation.isValidTitle(title1)) {
            status.setText("Incorrect title.");
        } else if (!Validation.isValidTitle(text1)) {
            status.setText("Incorrect text.");
        }
        Diary diary = new Diary(title1, text1);
        diaryDAO.create(diary);
        status.setText("Record has been added successfully.");
    }

    @FXML
    public void onUpdateButton(ActionEvent event) throws IOException {

    }

    @FXML
    public void onDeleteButton(ActionEvent event) throws IOException {

    }

    @FXML
    public void onSearchButton(ActionEvent event) throws IOException {
        String title1 = title.getText();
        List<Diary> list = diaryDAO.searchAllObjectOrByTitle(title1);
    }
    // TODO: 2022-03-14 FK, each diary records for each user. 

}
