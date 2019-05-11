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
