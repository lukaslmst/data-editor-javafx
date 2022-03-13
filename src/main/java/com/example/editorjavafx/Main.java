package com.example.editorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            EditorController.show(stage);
        }

        public static void main(String[] args) {
            launch();
        }
}