module com.example.diary {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires java.xml;

    opens com.example.diary to javafx.fxml;
    exports com.example.diary;

    opens com.example.diary.controller to javafx.fxml;
    exports com.example.diary.controller;

    opens com.example.diary.model to javafx.fxml;
    exports com.example.diary.model;
}