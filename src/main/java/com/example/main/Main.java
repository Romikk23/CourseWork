package com.example.main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import records.Records;

import java.io.IOException;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage stage) throws IOException {
        Records records = new Records();
        Parent root = FXMLLoader.load(getClass().getResource("visualInterface.fxml"));
        stage.setScene(new Scene(root, Color.TRANSPARENT));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}