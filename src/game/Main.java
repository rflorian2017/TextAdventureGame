package game;

import game.control.Controller;
import game.helper.sql.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        controller = loader.getController();

        primaryStage.setOnCloseRequest(event -> {
            controller.saveGameProgress(null);
        });

//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                Controller controller = loader.getController();
//
//                controller.saveGameProgress(null);
//            }
//        });
        primaryStage.show();
    }



    public static void main(String[] args) {

        // create the database and all the tables in the app
        PlayerWrapper playerWrapper = new PlayerWrapper();
        GameBoardWrapper gameBoardWrapper = new GameBoardWrapper();
        ArtifactPositionWrapper artifactPositionWrapper = new ArtifactPositionWrapper();
        ArtifactWrapper artifactWrapper = new ArtifactWrapper();
        ConnectedGameBoardsWrapper connectedGameBoardsWrapper = new ConnectedGameBoardsWrapper();
        playerWrapper.createTable();
        gameBoardWrapper.createTable();
        artifactPositionWrapper.createTable();
        artifactWrapper.createTable();
        connectedGameBoardsWrapper.createTable();

        launch(args);
    }
}
