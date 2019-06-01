package game.model.gamedata;


import game.helper.sql.ConnectedGameBoardsWrapper;
import game.helper.sql.GameBoardWrapper;
import game.model.CollectibleItem;
import game.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<GameBoard> gameBoards;
    private int currentBoardIndex;

    /**
     * Create a game object
     */
    public Game() {
        gameBoards = new ArrayList<>(); // initialize the list that will hold all the game boards of this game
        //COMPLETED: load game boards
        // get gameboards from Database
        GameBoardWrapper gameBoardWrapper = new GameBoardWrapper();
        ConnectedGameBoardsWrapper connectedGameBoardsWrapper = new ConnectedGameBoardsWrapper();

        gameBoards = gameBoardWrapper.getAllGameBoards();
        Map<Integer, List<Integer>> relatedBoards =  connectedGameBoardsWrapper.getAllRelatedGameBoards();

        if(gameBoards.size() == 0) {
            //just for tests - COMPLETED: remove in production
            GameBoard gameBoardForest = new GameBoard("forest", 10);
            GameBoard gameBoardDungeon = new GameBoard("dungeon", 9);

            gameBoards.add(gameBoardForest);
            gameBoards.add(gameBoardDungeon);

            gameBoardDungeon.addConnectedBoard(gameBoardForest, 0, 4);
            gameBoardForest.addConnectedBoard(gameBoardDungeon, 9, 9);

            currentBoardIndex = 0;
        }
        else  {
            if(relatedBoards.size() > 0) {
                for (GameBoard gameb: gameBoards
                     ) {
                    if(relatedBoards.containsKey(gameb.getUniqueId())) {
                        gameb.addConnectedBoard(getGameBoard(relatedBoards.get(gameb.getUniqueId()).get(0)),
                                relatedBoards.get(gameb.getUniqueId()).get(1),
                                relatedBoards.get(gameb.getUniqueId()).get(2));
                    }
                }
            }

        }
    }

    public GameBoard getGameBoard(int gameBoardUniqueId) {
        for (GameBoard gameBoard: gameBoards
             ) {
            if(gameBoard.getUniqueId() == gameBoardUniqueId) {
                return gameBoard;
            }
        }

        return null; // should never reach null
    }

    /**
     * This method will add an object to the board in the given position
     *
     * @param obj        to place
     * @param vertical   position
     * @param horizontal position
     * @return true if the object can be placed; false if there is already an object there,
     * or throw ArrayIndexOutOfBoundsException if the positions are outside the limits of
     * the board
     */
    public boolean placeOnBoard(Object obj, int horizontal, int vertical) {

        return gameBoards.get(currentBoardIndex).placeOnBoard(obj, horizontal, vertical);
    }

    /**
     * Removes an object from the board
     *
     * @param vertical   position
     * @param horizontal position
     * @return the object to remove; null if the cell is empty
     */
    public Object removeFromBoard(int horizontal, int vertical) {
        return gameBoards.get(currentBoardIndex).removeFromBoard(horizontal, vertical);
    }

    /**
     * Moves the object from the gameBoard to a new position
     *
     * @param player     to move
     * @param horizontal position
     * @param vertical   position
     *                   return true if you can move
     */
    public boolean movePlayer(Player player, int horizontal, int vertical) {

        //TODO: Allow player and collectible to be on the same box

        if (vertical >= gameBoards.get(currentBoardIndex).getBoardSize() ||
                horizontal >= gameBoards.get(currentBoardIndex).getBoardSize()) {
            throw new IllegalArgumentException("Should be smaller than " +
                    gameBoards.get(currentBoardIndex).getBoardSize());
        }

        boolean playerCanMove;// = false;

        if (gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical) == null) {
            playerCanMove = true;
        } else if (gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical) instanceof CollectibleItem) {
            player.collect((CollectibleItem) gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical));
            gameBoards.get(currentBoardIndex).removeFromBoard(horizontal, vertical); // remove collectible from board
            playerCanMove = true;
        } else {
            // item is nor collectible, nor null
            playerCanMove = false;
        }

        if (playerCanMove) {
            gameBoards.get(currentBoardIndex).removeFromBoard(player.getHorizontal(), player.getVertical()); // remove player from his/hers old position

            GameBoard connectedBoard = gameBoards.get(currentBoardIndex).isAPortalToOtherGameBoard(horizontal, vertical);
            if (null != connectedBoard) {
                for (int i = 0; i < gameBoards.size(); i++) {
                    if (gameBoards.get(i).equals(connectedBoard)) {

                        // see in the Map of the new board, the link to the old board and get coordinates
                        // gameBoards.get(currentBoardIndex) .getHor (connectedBoard)

                        int newHorizontal = gameBoards.get(currentBoardIndex).getPortalHorizontal(connectedBoard);
                        int newVertical = gameBoards.get(currentBoardIndex).getPortalVertical(connectedBoard);
                        currentBoardIndex = i;
                        gameBoards.get(currentBoardIndex).placeOnBoard(player, newHorizontal, newVertical); // place player in the new position given by parameters
                        player.setPosition(newHorizontal, newVertical);
                        break;
                    }
                }
            }

            else {
                gameBoards.get(currentBoardIndex).placeOnBoard(player, horizontal, vertical); // place player in the new position given by parameters
                player.setPosition(horizontal, vertical);
            }



        }

        return playerCanMove;
    }

    public String displayBoard() {
        return gameBoards.get(currentBoardIndex).toString();
    }

    public int getCurrentBoardIndex() {
        return currentBoardIndex;
    }

    public void setCurrentBoardIndex(int currentBoardIndex) {
        this.currentBoardIndex = currentBoardIndex;
    }

    public List<GameBoard> getGameBoards() {
        return gameBoards;
    }


}
