package game.model;

public interface CollectibleItem {
    default boolean isCollectible() {
        return true;
    }
}
