package com.example.editorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.LinkedList;

public class Connect {

    private Connection conn;


    public void connect(PasswordField password, TextField username, TextField dbString, TreeView panel) throws SQLException {


        String passwordStr = password.getText();
        String usernameStr = username.getText();
        String dbStringStr = dbString.getText();

         conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@delphi.htl-leonding.ac.at:1521:delphidb", "it190225", "oracle"
        );
         showTables(panel);

    }

    public void showTables(TreeView panel) throws SQLException {

        //jdbc:oracle:thin:@delphi.htl-leonding.ac.at:1521:delphidb

        TreeItem rootitem = new TreeItem("Tables");

        panel.setRoot(rootitem);
        LinkedList item = new LinkedList<TreeItem>();
        LinkedList column = new LinkedList<TreeItem>();

        PreparedStatement pstmt = conn.prepareStatement("select table_name from user_tables");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            String tableName = rs.getString("table_name");

            TreeItem tableItem = new TreeItem(tableName);

            rootitem.getChildren().add(tableItem);

            PreparedStatement ptsmt2 = conn.prepareStatement("select * from " + tableName);
            ResultSet rs2 = ptsmt2.executeQuery();

            for (int i = 0; i < rs2.getMetaData().getColumnCount(); i++) {

                TreeItem columnItem = new TreeItem(rs2.getMetaData().getColumnName(i + 1));
                tableItem.getChildren().add(columnItem);

            }

        }

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
