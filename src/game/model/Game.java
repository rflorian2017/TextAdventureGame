package game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameBoard> gameBoards;
    private int currentBoardIndex;

    /**
     * Create a game object
     */
    public Game() {
        gameBoards = new ArrayList<>(); // initialize the list that will hold all the game boards of this game
        //TODO: load game boards
        currentBoardIndex = 0;
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
        if (vertical >= gameBoards.get(currentBoardIndex).getBoardSize() ||
                horizontal >= gameBoards.get(currentBoardIndex).getBoardSize()) {
            throw new IllegalArgumentException("Should be smaller than " +
                    gameBoards.get(currentBoardIndex).getBoardSize());
        }
        boolean returnType;// = false;

        if (gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical) == null) {
            returnType = true;
        } else if (gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical) instanceof CollectibleItem) {
            player.collect((CollectibleItem) gameBoards.get(currentBoardIndex).getGameBoardObject(horizontal, vertical));
            returnType = true;
        } else {
            // item is nor collectible, nor null
            returnType = false;
        }

        if (returnType) {
            gameBoards.get(currentBoardIndex).removeFromBoard(player.getHorizontal(), player.getVertical());
            gameBoards.get(currentBoardIndex).placeOnBoard(player, horizontal, vertical);
            player.setPosition(horizontal, vertical);
        }

        return returnType;
    }
}
