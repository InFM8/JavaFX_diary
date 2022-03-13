module com.example.diary {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.example.diary to javafx.fxml;
    exports com.example.diary;
    exports com.example.diary.controller;
    opens com.example.diary.controller to javafx.fxml;
}