package game;

import game.helper.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }



    public static void main(String[] args) {

        // create the database and all the tables in the app
        PlayerWrapper playerWrapper = new PlayerWrapper();
        GameBoardWrapper gameBoardWrapper = new GameBoardWrapper();
        ArtifactPositionWrapper artifactPositionWrapper = new ArtifactPositionWrapper();
        ArtifactWrapper artifactWrapper = new ArtifactWrapper();
        playerWrapper.createTable();
        gameBoardWrapper.createTable();
        artifactPositionWrapper.createTable();
        artifactWrapper.createTable();

        launch(args);
    }
}
