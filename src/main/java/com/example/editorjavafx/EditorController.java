package com.example.editorjavafx;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EditorController {

    @FXML
    private TextArea queryField;
    @FXML
    private Button executeBtn;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private TextField dbString;
    @FXML
    private TreeView<String> panel;
    @FXML
    private Button connectBtn;
    @FXML
    private TableView tableOut;
    @FXML
    private TableView dataField;
    Connect connection = new Connect();

    boolean connected = false;


    public static void show(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void executeQuery(ActionEvent event) throws SQLException {

        if(connected) {

            String temp = queryField.getText();

            String queryArray[] = temp.split(";");

            for (int i = 0; i < queryArray.length; i++) {

                System.out.println(queryArray[i]);

                PreparedStatement exe = connection.getConn().prepareStatement(queryArray[i]);
                ResultSet rs = exe.executeQuery();

                while (rs.next()) {
                    for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
                        System.out.println(rs.getString(j + 1));

                    }
                }

            }
        }

    }

    public void showData() throws SQLException {


        TreeItem<String> temp =  panel.getSelectionModel().getSelectedItem();

        if(temp == null)
            return;

        String tableName = temp.getValue();

        PreparedStatement pts =  connection.getConn().prepareStatement("select * from "+tableName);
        ResultSet rs = pts.executeQuery();

        List<List<String>> data =new ArrayList<>();

        while (rs.next()){

           List tempList = new ArrayList();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                tempList.add(rs.getString(i+1));
            }
            data.add(tempList);

        }
        System.out.println(data);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            final int id = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(id+1));

            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(id));
                }
            });
            dataField.getColumns().add(col);
        }
        data.remove(0);
        dataField.setItems(FXCollections.observableArrayList(data));

    }


    public void connect(ActionEvent event) throws SQLException {
        connected = true;
        connection.connect(password, username, dbString, panel);
    }
}