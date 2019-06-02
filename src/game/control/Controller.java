package game.control;

import game.helper.sql.*;
import game.model.*;
import game.model.Thread.DisplayFileName;
import game.model.gamedata.Game;
import game.model.gamedata.GameBoard;
import game.model.gamedata.GameStrategy;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Controller {

    public TextField txtFieldCommand;
    public TextArea txtAreaGameOutput;
    public Pane rootPane;
    public ProgressBar progressBar;
    public ProgressIndicator progressIndicator;
    public Button btnStart;
    private DisplayFileName displayFileNameTask;

    private Game game;
    private Player player;

    public void initialize() {
        game = new Game();

        progressBar.setProgress(0.5);
        progressIndicator.setProgress(0.2);

        List<Artifact> artifacts = new ArtifactWrapper().getAllArtifacts();
        Map<Integer, List<Integer>> artifactsPositions =
                new ArtifactPositionWrapper().getAllArtifactsPositions();

        Map<Integer, Integer> artifactsGameBorads = new ArtifactPositionWrapper().getAllArtifactsGameboardsMap();
        if (artifacts.size() == 0) {
            Key key = new Key();
            Door door = new Door(key);

            game.placeOnBoard(key, 2, 3, 0);
            game.placeOnBoard(door, 1, 5, 0);

        } else {
            for (Artifact artifact : artifacts
            ) {
                game.placeOnBoard(artifact,
                        artifactsPositions.get(artifact.getId()).get(1),
                        artifactsPositions.get(artifact.getId()).get(0),
                        artifactsGameBorads.get(artifact.getId()));
            }
        }

        List<Player> players = new PlayerWrapper().getAllPlayers();
        if (players.size() == 0) {

            player = new Player();
            game.placeOnBoard(player, 5, 5, 0);
        } else {

            player = players.get(0);
            // only one player in database
            // player.setPosition(players.get(0).getHorizontal(), players.get(0).getVertical());
            game.setCurrentBoardIndex(player.getCurrentGameBoard());
            game.placeOnBoard(player, player.getHorizontal(),
                    player.getVertical(), player.getCurrentGameBoard());
        }

        System.out.println(game.displayBoard());
        txtAreaGameOutput.appendText(game.displayBoard() + "\n");

    }

    public void processCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            GameStrategy.processCommand(txtFieldCommand.getText(), game, player);
            txtFieldCommand.clear();
            txtAreaGameOutput.appendText(game.displayBoard() + "\n");
        } else {
            GameStrategy.processArrowCommand(keyEvent, game, player);
            txtAreaGameOutput.appendText(game.displayBoard() + "\n");
        }
    }

    public void saveGameProgress(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm saving");
        alert.setHeaderText("Are you sure you want to save?");
        alert.setContentText("Really , really ?");

        ButtonType buttonTypeYes =  new ButtonType("Yes");
        ButtonType buttonTypeNo =  new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeYes) {
            PlayerWrapper playerWrapper = new PlayerWrapper();
            GameBoardWrapper gameBoardWrapper = new GameBoardWrapper();
            ArtifactPositionWrapper artifactPositionWrapper = new ArtifactPositionWrapper();
            ArtifactWrapper artifactWrapper = new ArtifactWrapper();
            ConnectedGameBoardsWrapper connectedGameBoardsWrapper = new ConnectedGameBoardsWrapper();

            playerWrapper.delete();
            gameBoardWrapper.delete();
            artifactPositionWrapper.delete();
            artifactWrapper.delete();
            connectedGameBoardsWrapper.delete();

            for (int i = 0; i < game.getGameBoards().size(); i++) {
                GameBoard gameBoard = game.getGameBoards().get(i);
                gameBoardWrapper.insert(gameBoard);
                for (Map.Entry<Artifact, List<Integer>> entry : gameBoard.getArtifactsPositions().entrySet()) {
                    artifactWrapper.insert(entry.getKey());
                    artifactPositionWrapper.insert(entry.getKey(), gameBoard);
                }

                connectedGameBoardsWrapper.insertRelatedBoards(gameBoard);

            }

            playerWrapper.insert(player, game.getCurrentBoardIndex());

        }

        else {
            // do nothing
        }
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    public void startThread(ActionEvent event) {
        displayFileNameTask = new DisplayFileName();


        progressBar.progressProperty().bind(displayFileNameTask.progressProperty());
        progressIndicator.progressProperty().bind(displayFileNameTask.progressProperty());

        txtAreaGameOutput.textProperty().unbind();
        txtAreaGameOutput.textProperty().bind(displayFileNameTask.messageProperty());

        displayFileNameTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                event1 -> {
                    txtAreaGameOutput.textProperty().unbind();
                    txtAreaGameOutput.setText("finished");
                });
        // Start the Task.
        new Thread(displayFileNameTask).start();
    }

    public void cancelTask(ActionEvent event) {
        displayFileNameTask.cancel(true);
    }
}
