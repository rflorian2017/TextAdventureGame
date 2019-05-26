package game.control;

import game.helper.sql.*;
import game.model.*;
import game.model.gamedata.Game;
import game.model.gamedata.GameBoard;
import game.model.gamedata.GameStrategy;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    public TextField txtFieldCommand;
    public TextArea txtAreaGameOutput;

    private Game game;
    private Player player;

    public void initialize() {
        game = new Game();


        List<Artifact> artifacts = new ArtifactWrapper().getAllArtifacts();
        Map<Integer, List<Integer>> artifactsPositions =
                new ArtifactPositionWrapper().getAllArtifactsPositions();
         if(artifacts.size() == 0) {
            Key key = new Key();
            Door door = new Door(key);

            game.placeOnBoard(key, 2, 3);
            game.placeOnBoard(door, 1, 5);

        }

        else {
            for (Artifact artifact : artifacts
                 ) {
                game.placeOnBoard(artifact,
                        artifactsPositions.get(artifact.getId()).get(1),
                        artifactsPositions.get(artifact.getId()).get(0));
            }
        }

        List<Player> players = new PlayerWrapper().getAllPlayers();
        if(players.size() == 0) {

            player = new Player();
            game.placeOnBoard(player, 5, 5);
        }

        else {
            for (Player player_: players
                 ) {
                game.placeOnBoard(player_, player_.getHorizontal(),
                        player_.getVertical());
            }
        }





        System.out.println(game.displayBoard());
        txtAreaGameOutput.appendText(game.displayBoard() + "\n");

//        game.removeFromBoard(2, 3);
//        System.out.println(game.displayBoard());
//        txtAreaGameOutput.appendText(game.displayBoard());
//
//        game.placeOnBoard(key, 2, 3);
//        System.out.println(player.getArtifacts());
//        game.movePlayer(player, 2,3);
//        System.out.println(game.displayBoard());
//        txtAreaGameOutput.appendText(game.displayBoard());
//        System.out.println(player.getArtifacts());
//        txtAreaGameOutput.appendText(player.getArtifacts());
//
//        game.movePlayer(player, 1,5);
//        System.out.println(game.displayBoard());
//        txtAreaGameOutput.appendText(game.displayBoard());
//        System.out.println(player.getArtifacts());
//        txtAreaGameOutput.appendText(player.getArtifacts());

    }

    public void processCommand(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            GameStrategy.processCommand(txtFieldCommand.getText(), game, player);
            txtFieldCommand.clear();
            txtAreaGameOutput.appendText(game.displayBoard() + "\n");
        }
    }

    public void saveGameProgress(ActionEvent event) {
        PlayerWrapper playerWrapper = new PlayerWrapper();
        GameBoardWrapper gameBoardWrapper = new GameBoardWrapper();
        ArtifactPositionWrapper artifactPositionWrapper = new ArtifactPositionWrapper();
        ArtifactWrapper artifactWrapper = new ArtifactWrapper();

        for(int i=0; i<game.getGameBoards().size(); i++) {
            GameBoard gameBoard = game.getGameBoards().get(i);
            gameBoardWrapper.insert(gameBoard);
            for (Map.Entry<Artifact, List<Integer>> entry : gameBoard.getArtifactsPositions().entrySet())
            {
                artifactWrapper.insert(entry.getKey());
                artifactPositionWrapper.insert(entry.getKey(), gameBoard);
            }

        }

        playerWrapper.insert(player, game.getCurrentBoardIndex());


    }
}
