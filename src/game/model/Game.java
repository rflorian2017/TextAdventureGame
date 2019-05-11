package game.model;

public class Game {
    private Object[][] gameBoard;

    /**
     * Create a game object
     * @param gameBoardSize gives the size of the array that keeps artifacts
     */
    public Game(int gameBoardSize) {
        gameBoard = new Object[gameBoardSize][gameBoardSize];
    }


    /**
     * This method will add an object to the board in the given position
     * @param obj to place
     * @param vertical position
     * @param horizontal position
     * @return true if the object can be placed; false if there is already an object there,
     * or throw ArrayIndexOutOfBoundsException if the positions are outside the limits of
     * the board
     */
    public boolean placeOnBoard(Object obj, int vertical, int horizontal) {
        return false;
    }

    /**
     * Removes an object from the board
     * @param vertical position
     * @param horizontal position
     * @return the object to remove; null if the cell is empty
     */
    public Object removeFromBoard(int vertical, int horizontal) {
        return null;
    }
}
