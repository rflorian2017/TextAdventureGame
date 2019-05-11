package game.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /**
     * items that the player can collect
     */
    private List<Artifact> artifacts;

    /**
     * Create a player that has a list of collected items
     */
    public Player() {
        artifacts = new ArrayList<>();
    }
}
