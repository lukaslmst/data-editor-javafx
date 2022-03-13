package com.example.editorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.*;

public class EditorController {


    public static void show(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button connectBtn;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private TextField dbString;

    public void connect(ActionEvent event) throws SQLException {

        String passwordStr = password.getText();
        String usernameStr = username.getText();
        String dbStringStr = dbString.getText();

        Connection conn = DriverManager.getConnection(
                dbStringStr,usernameStr,passwordStr
        );

        //jdbc:oracle:thin:@delphi.htl-leonding.ac.at:1521:delphidb
        PreparedStatement pstmt = conn.prepareStatement("select * from emp");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("EMPNO"));
        }


    }

}