module com.example.editorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    requires java.sql;

    opens com.example.editorjavafx to javafx.fxml;
    exports com.example.editorjavafx;
}