module com.example.appl {
    requires javafx.controls;
    requires com.jfoenix;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
}