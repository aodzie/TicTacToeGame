package com.game.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Fields {
    private Messages messages;
    private StackPane pane;
    private SingleField[][] allFields = new SingleField[3][3];
    private char player = 'X';
    private boolean isEnd = false;

    public Fields(Messages messages){
        this.messages = messages;
        pane = new StackPane();
        pane.setPrefSize(300,300);
        pane.setTranslateX(150);
        pane.setTranslateY(150);
        addAllFields();
    }

    private void addAllFields() {
        for (int row = 0; row<3; row++){
            for (int column=0; column<3; column++){
                SingleField field = new SingleField();
                field.getPane().setTranslateX((column*100)-100);
                field.getPane().setTranslateY((row*100)-100);
                pane.getChildren().add(field.getPane());
                allFields[row][column] = field;
            }
        }
    }

    public void changePlayer(){
        if (player == 'X') player = 'O';
        else player = 'X';
        messages.updateMessage("Player " + player + " turn");
    }

    public String getPlayer(){
        return String.valueOf(player);
    }

    public StackPane getPane(){
        return pane;
    }
    public void checkIfWon(){
        checkRows();
        checkColumns();
        checkDiagonalTopLeft();
        checkDiagonalTopRight();
        checkTie();
    }

    private void checkRows() {
        if (!isEnd){
            for (int row=0; row<3; row++) {
                if (!allFields[row][0].getLabel().isEmpty()
                        && allFields[row][0].getLabel().equals(allFields[row][1].getLabel()) &&
                        allFields[row][1].getLabel().equals(allFields[row][2].getLabel())) {
                    String winner = allFields[row][0].getLabel();
                    endGame(winner);
                    return;
                }
            }
        }
    }

    private void checkColumns() {
        if (!isEnd){
            for (int column=0; column<3; column++){
                if (!allFields[0][column].getLabel().isEmpty()
                        &&allFields[0][column].getLabel().equals(allFields[1][column].getLabel()) &&
                        allFields[1][column].getLabel().equals(allFields[2][column].getLabel()) ) {
                    String winner = allFields[0][column].getLabel();
                    endGame(winner);
                    return;
                }
            }
        }
    }

    private void checkDiagonalTopLeft() {
        if (!isEnd){
            if (!allFields[0][0].getLabel().isEmpty() &&
                    allFields[0][0].getLabel().equals(allFields[1][1].getLabel()) &&
                    allFields[1][1].getLabel().equals(allFields[2][2].getLabel()) &&
                    allFields[0][0].getLabel().equals(allFields[2][2].getLabel())
            ){
                String winner = allFields[0][0].getLabel();
                endGame(winner);
            }
        }
    }
    private void checkDiagonalTopRight() {
        if (!isEnd){
            if (!allFields[0][2].getLabel().isEmpty() &&
                    allFields[0][2].getLabel().equals(allFields[1][1].getLabel()) &&
                    allFields[1][1].getLabel().equals(allFields[2][0].getLabel()) &&
                    allFields[2][0].getLabel().equals(allFields[0][2].getLabel())
            ){
                String winner = allFields[0][2].getLabel();
                endGame(winner);
            }
        }
    }
    private void checkTie() {
        if (!isEnd) {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (allFields[row][column].getLabel().isEmpty()) {
                        return;
                    }
                }
            }

            isEnd = true;
            messages.updateMessage("There is a tie!");
        }
    }

    private void endGame(String winner) {
        isEnd = true;
        messages.updateMessage("Winner " + winner);
    }
    private class SingleField {
        private StackPane pane;
        private Label label;

        public SingleField(){
            pane = new StackPane();
            pane.setMinSize(100,100);

            label = new Label ("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(30));
            label.setTextFill(Color.BLACK);
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if(label.getText().isEmpty() && !isEnd){
                    label.setText(getPlayer());
                    checkIfWon();
                    if (!isEnd){
                    changePlayer();}
                }
            });
        }

        public StackPane getPane(){
            return pane;
        }

        public String getLabel(){
            return label.getText();
        }
    }

}