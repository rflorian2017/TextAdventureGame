package game.model;

public class GameBoard {
    private String boardName; // the name of the current "map" where a player can be at a time

    private int boardSize; // the game board will have an array of boardSize x boardSize

    private Object[][] gameBoardObjects; // the actual board where the artifacts/actors/player will rest

    public GameBoard(String boardName, int boardSize) {
        this.boardName = boardName;
        this.boardSize = boardSize;
        gameBoardObjects = new Object[boardSize][boardSize];
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
        if (vertical >= boardSize || horizontal >= boardSize) {
            throw new IllegalArgumentException("Should be smaller than " + boardSize);
        }

        if (gameBoardObjects[horizontal][vertical] != null) {
            return false;
        } else {
            gameBoardObjects[horizontal][vertical] = obj;
        }

        if (obj instanceof Player) {
            ((Player) obj).setPosition(horizontal, vertical);
        }
        return true;
    }

    /**
     * Removes an object from the board
     *
     * @param vertical   position
     * @param horizontal position
     * @return the object to remove; null if the cell is empty
     */
    public Object removeFromBoard(int horizontal, int vertical) {
        if (vertical >= boardSize || horizontal >= boardSize) {
            throw new IllegalArgumentException("Should be smaller than " + boardSize);
        }

        Object toRemove = gameBoardObjects[horizontal][vertical];
        gameBoardObjects[horizontal][vertical] = null;

        return toRemove;
    }

    /*
        Door      Key                        Player
                 Tree

             a cell is maximum 10 spaces size   . We put an artifact in the middle of the cell
     */
    private final int CELL_DISPLAY_SIZE = 12;

    private String generateSpaces(int number) {
        String spaces = "";
        for (int i = 0; i < number; i++) {
            spaces += " ";
        }
        return spaces;
    }

    private String centerCell(String input) {
        String output = "";
        output += generateSpaces((CELL_DISPLAY_SIZE - input.length()) / 2) +
                input +
                generateSpaces((CELL_DISPLAY_SIZE - input.length()) / 2);
        return output;
    }

    @Override
    public String toString() {
        String toReturn = "";

        /* generate head of table */
        toReturn += generateSpaces(CELL_DISPLAY_SIZE);
        for (int i = 0; i < boardSize; i++) {
            toReturn += centerCell((i + 1) + " ");

        }

        toReturn += "\n";

        // print game board contents
        for (int i = 0; i < boardSize; i++) {
            toReturn += centerCell((i + 1) + "");
            for (int j = 0; j < boardSize; j++) {
                if (gameBoardObjects[i][j] == null) {
                    toReturn += generateSpaces(CELL_DISPLAY_SIZE);
                } else {
                    toReturn += centerCell(gameBoardObjects[i][j].getClass().getSimpleName());
                }
            }
            toReturn += "\n";
        }

        return toReturn;
    }
}
