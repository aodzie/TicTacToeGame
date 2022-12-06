package com.game.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Messages {
    private Label message;
    private StackPane pane;

    public Messages(){
        pane = new StackPane();
        pane.setPrefSize(300, 100);
        pane.setTranslateX(150);
        pane.setTranslateY(350);

        message = new Label("Player X turn");
        message.setMinSize(300,100);
        message.setTextFill(Color.BLACK);
        message.setFont(new Font("Arial", 30));

        pane.getChildren().add(message);
    }

    public void updateMessage(String message){
        this.message.setText(message);
    }

    public StackPane getPane(){
        return pane;
    }
}
