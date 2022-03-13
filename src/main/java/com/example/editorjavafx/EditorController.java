package com.example.editorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EditorController {


    public static void show(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button connectBtn;

    public void connect(ActionEvent event) throws SQLException {

        String password;
        String username;
        String dbString;

        String dbstring = "jdbc:oracle:thin:@delphi.htl-leonding.ac.at:1521:delphidb";

        Connection conn = DriverManager.getConnection(
                dbstring,"it190225","oracle"
        );


    }

}