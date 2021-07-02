package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.*;


public class TicTacToeApp extends Application {
    private Stage stage;
    private Scene scene1, scene2, scene3, scene4;
    private final String filepath = "src/main/resources/rankings.csv";
    private TextField textField;
    private TextArea textArea;
    private Content game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene1 = createScene1();
        scene2 = createScene2();
        scene3 = createScene3();
        scene4 = createScene4();

        stage = primaryStage;
        stage.setTitle("TicTacToeGame");
        stage.setResizable(false);
        stage.setScene(scene1);
        stage.show();
    }

    private Scene createScene2() {
        game = new Content();
        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setStyle("-fx-background-color: slategrey;" +
                "-fx-text-fill: white");
        backToMenuButton.setOnAction(e ->switchScene(scene1));
        Button saveResult = new Button("Save Result");
        saveResult.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");
        saveResult.setOnAction(e -> saveData(textField.getText(), game.wins, game.losses, game.ties, game.numOfGames));
        VBox vbox2 = new VBox(backToMenuButton, saveResult, game);
        vbox2.setStyle("-fx-background-color: cornflowerblue");
        scene2 = new Scene(vbox2, 600, 600);

        return scene2;
    }
    private void saveData(String name, int wins, int losses, int ties, int games){
        try{
            FileWriter fileWriter = new FileWriter(filepath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(name + "," + games + "," + wins + "," + losses + "," + ties);
            printWriter.flush();
            printWriter.close();

        }catch(Exception exception){
            exception.printStackTrace();
        }

    }
    private void displayData(){
        String line;
        try {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                String[] values = line.split(",");
                textArea.appendText("Name: " + values[0] + ", games: " + values[1] +
                        ", wins: " + values[2] + ", losses: " + values[3] + ", ties: " + values[4] + "\n");
            }
        }catch (Exception exception){
            exception.printStackTrace();

        }
    }
    private Scene createScene3()  {
        Text welcome = new Text("Welcome in TicTacToe Game.\nEnter your name and click Start Game.");
        welcome.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 20));
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setTranslateY(-100);

        textField = new TextField();
        textField.setAlignment(Pos.CENTER);

        Button start = new Button("Start Game");
        start.setPrefSize(100,100);
        start.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");
        start.setOnAction(e -> switchScene(scene2));

        VBox vbox3 = new VBox(20, welcome, textField,start);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setStyle("-fx-background-color: cornflowerblue");
        scene3 = new Scene(vbox3, 600, 600);
        return scene3;
    }

    private void switchScene(Scene scene) {
        stage.setScene(scene);
    }

    private Scene createScene4(){
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setStyle("-fx-border-color: black");
        textArea.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 16));
        displayData();

        Button backToMenuButton = new Button("Back to menu");
        backToMenuButton.setStyle("-fx-background-color: slategrey;" +
                "-fx-text-fill: white");
        backToMenuButton.setOnAction(e -> switchScene(scene1));
        Button refresh = new Button ("Refresh Ranking List");
        refresh.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");
        refresh.setTranslateX(250);
        refresh.setOnAction(e-> {
            textArea.clear();
            displayData();
        });

        VBox vbox5 = new VBox(20, backToMenuButton, refresh, textArea);
        vbox5.setStyle("-fx-background-color: cornflowerblue");
        scene4 = new Scene(vbox5, 600, 600);
        return scene4;
    }
    private Scene createScene1() {
        Button startGameButton = new Button("Start Game");
        startGameButton.setPrefSize(100,100);
        startGameButton.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");

        startGameButton.setOnAction(e -> switchScene(scene3));

        Button rankingsButton = new Button("Rankings");
        rankingsButton.setPrefSize(100,100);
        rankingsButton.setStyle("-fx-background-color: slategrey;" +
                "-fx-text-fill: white");
        rankingsButton.setOnAction(e -> switchScene(scene4));

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setPrefSize(100,100);
        exitGameButton.setStyle("-fx-background-color: slateblue;" +
                "-fx-text-fill: white");
        exitGameButton.setOnAction(e -> stage.close());

        VBox vbox1 = new VBox(20, startGameButton, rankingsButton, exitGameButton);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setStyle("-fx-background-color: cornflowerblue");
        scene1 = new Scene(vbox1, 600, 600);

        return scene1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
