package com.kodilla.tictactoe;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class Content extends VBox {

    private final Button[][] buttonsBoard;
    private final Label label, winsLabel, lossesLabel, tiesLabel, numOfGamesLabel;
    int turn, wins, losses, ties, numOfGames;
    private boolean endGame = false;

    public Content(){
        buttonsBoard = new Button[3][3];
        GridPane board = new GridPane();
        board.setPrefSize(600,600);
        board.setAlignment(Pos.CENTER);

        for(int c = 0; c < 3; c ++){
            for(int r = 0; r < 3; r ++){
                buttonsBoard[c][r] = new Button();
                buttonsBoard[c][r].setPrefSize(150,150);
                buttonsBoard[c][r].setStyle("-fx-background-color: transparent;" +
                        "-fx-border-color: black");
                board.add(buttonsBoard[c][r], c, r);
                buttonsBoard[c][r].setOnAction(this::buttonAction);
            }
        }
        //main label
        label = new Label("You start. Take a spot");
        label.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 27));
        label.setTranslateY(10);
        //statistics labels
        winsLabel = new Label("Wins: " + wins);
        winsLabel.setFont(Font.font("Ink Free",FontWeight.BOLD, FontPosture.ITALIC, 16));
        lossesLabel = new Label ("Losses: " + losses);
        lossesLabel.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 16));
        tiesLabel = new Label("Ties: " + ties);
        tiesLabel.setFont(Font.font("Ink Free",FontWeight.BOLD, FontPosture.ITALIC, 16));
        numOfGamesLabel = new Label("Games: " + numOfGames);
        numOfGamesLabel.setFont(Font.font("Ink Free",FontWeight.BOLD, FontPosture.ITALIC, 16));
        HBox labelBox = new HBox(winsLabel, lossesLabel, tiesLabel, numOfGamesLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setTranslateY(-10);
        labelBox.setTranslateX(-10);
        labelBox.setSpacing(20);
        VBox root = new VBox(board, labelBox);

        Button playAgain = new Button("Play Again");
        playAgain.setTranslateX(200);
        playAgain.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");
        playAgain.setOnAction(e -> resetGame());

        setAlignment(Pos.CENTER);
        getChildren().addAll(label, playAgain, root);

    }
    private void resetGame(){
        for(int c = 0; c < 3; c ++) {
            for (int r = 0; r < 3; r++) {
                buttonsBoard[c][r].setText("");
                buttonsBoard[c][r].setStyle("-fx-background-color: transparent;" +
                        "-fx-border-color: black");
            }
        }
        endGame = false;
        turn = 0;
        label.setText("You start. Take a spot");
    }

    private void buttonAction(ActionEvent event) {
        if (!endGame) {
            String text;
            Button button = (Button)event.getSource();

            if (button.getText().length() > 0) {
                if (turn % 2 != 0) {
                    opponentMove();
                }
                return;
            }

            if (turn % 2 == 0) {
                text = "X";
            }else {
                text = "O";
            }
            turn++;
            button.setFont(Font.font(60));
            button.setText(text);

            //after 5 turns start checking for winner
            if(turn >=5){
                if (isWinner(text)){
                    label.setText(text + " is a winner");
                    if (text.equals("X")) {
                        winsLabel.setText("Wins: " + (++ wins));
                    }else if (text.equals("O")){
                        lossesLabel.setText("Losses: " + (++ losses));
                    }
                    endGame = true;
                    numOfGamesLabel.setText("Games: " + (++ numOfGames));
                    return;
                }
            }
            //9 turns - every spot taken
            if (turn == 9) {
                label.setText("Tie");
                tiesLabel.setText("Ties: " + (++ties));
                numOfGamesLabel.setText("Games: " + (++ numOfGames));
                endGame = true;
            }
            if (turn % 2 != 0) {
                opponentMove();
            }
        }
    }

    private void opponentMove() {
        Random random = new Random();
        int c = random.nextInt(3);
        int r = random.nextInt(3);
        buttonsBoard[c][r].fire();
    }
    private boolean isWinner(String player){
        //horizontal
        if (player.equals(buttonsBoard[0][0].getText()) &&
                player.equals(buttonsBoard[1][0].getText()) &&
                player.equals(buttonsBoard[2][0].getText())) {
            buttonsBoard[0][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        if (player.equals(buttonsBoard[0][1].getText()) &&
                player.equals(buttonsBoard[1][1].getText()) &&
                player.equals(buttonsBoard[2][1].getText())) {
            buttonsBoard[0][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        if (player.equals(buttonsBoard[0][2].getText()) &&
                player.equals(buttonsBoard[1][2].getText()) &&
                player.equals(buttonsBoard[2][2].getText())) {
            buttonsBoard[0][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        //vertical
        if (player.equals(buttonsBoard[0][0].getText()) &&
                player.equals(buttonsBoard[0][1].getText()) &&
                player.equals(buttonsBoard[0][2].getText())) {
            buttonsBoard[0][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[0][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[0][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        if (player.equals(buttonsBoard[1][0].getText()) &&
                player.equals(buttonsBoard[1][1].getText()) &&
                player.equals(buttonsBoard[1][2].getText())) {
            buttonsBoard[1][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        if (player.equals(buttonsBoard[2][0].getText()) &&
                player.equals(buttonsBoard[2][1].getText()) &&
                player.equals(buttonsBoard[2][2].getText())) {
            buttonsBoard[2][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        //diagonal
        if(player.equals(buttonsBoard[0][0].getText()) &&
                player.equals(buttonsBoard[1][1].getText()) &&
                player.equals(buttonsBoard[2][2].getText())) {
            buttonsBoard[0][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[2][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        if(player.equals(buttonsBoard[2][0].getText()) &&
                player.equals(buttonsBoard[1][1].getText()) &&
                player.equals(buttonsBoard[0][2].getText())) {
            buttonsBoard[2][0].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[1][1].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            buttonsBoard[0][2].setStyle("-fx-background-color: skyblue;" +
                    "-fx-border-color: black");
            return true;
        }
        return false;
    }
}
