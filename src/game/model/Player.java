package game.model;

import java.util.ArrayList;
import java.util.List;

public class Player implements ILocalizable {
    private int horizontal;
    private int vertical;

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    private int ID;

    private int currentGameBoard;

    private String name;

    public int getCurrentGameBoard() {
        return currentGameBoard;
    }

    public void setCurrentGameBoard(int currentGameBoard) {
        this.currentGameBoard = currentGameBoard;
    }

    public Player(String name, int ID) {
        this.name = name;
        artifacts = new ArrayList<>();
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public int getID() {
        return this.ID;
    }


    /**
     * items that the player can collect
     */
    private List<CollectibleItem> artifacts;

    /**
     * Create a player that has a list of collected items
     */
    public Player() {
        artifacts = new ArrayList<>();
    }

    public void collect(CollectibleItem item) {
        artifacts.add(item);
    }

    @Override
    public void setPosition(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public String getArtifacts() {
        return artifacts.toString();

    }
}
