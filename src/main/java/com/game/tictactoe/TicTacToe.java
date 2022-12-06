package com.game.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class TicTacToe extends Application {
    private final Image image = new Image("file:src/main/resources/background.png");
    private Messages messages;
    private Fields fields;

    private Parent createContent() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true,
                true, false);

       BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
       Background background = new Background(backgroundImage);

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(background);
        borderPane.setPrefSize(300, 400);
        
        initMessage(borderPane);
        initFields(borderPane);
        return borderPane;
    }

    private void initMessage(BorderPane borderPane) {
        messages = new Messages();
        borderPane.getChildren().add(messages.getPane());
    }

    private void initFields(BorderPane borderPane) {
        fields = new Fields(messages);
        borderPane.getChildren().add(fields.getPane());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("TicTacToe Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}