package game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameBoard> gameBoards;

    /**
     * Create a game object
     *
     */
    public Game() {
        gameBoards = new ArrayList<>(); // initialize the list that will hold all the game boards of this game
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

        return false;
    }

    /**
     * Removes an object from the board
     *
     * @param vertical   position
     * @param horizontal position
     * @return the object to remove; null if the cell is empty
     */
    public Object removeFromBoard(int horizontal, int vertical) {
        return null;
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
        if (vertical >= gameBoard.length || horizontal >= gameBoard.length) {
            throw new IllegalArgumentException("Should be smaller than " + gameBoard.length);
        }
        if (gameBoard[horizontal][vertical] instanceof CollectibleItem) {
            player.collect( (CollectibleItem) gameBoard[horizontal][vertical] );
            gameBoard[player.getHorizontal()][player.getVertical()] = null;
            gameBoard[horizontal][vertical] = player;
            player.setPosition(horizontal, vertical);
            return true;
        }
        else if(gameBoard[horizontal][vertical] == null) {
            gameBoard[player.getHorizontal()][player.getVertical()] = null;
            gameBoard[horizontal][vertical] = player;
            player.setPosition(horizontal, vertical);
            return true;
        }

        return false;
    }
}
