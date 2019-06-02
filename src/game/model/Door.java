package game.model;

public class Door extends Artifact {
    private Key doorKey;

    public Door(Key doorKey) {
        this.doorKey = doorKey;
    }

    public Door(int id, Key doorKey) {
        super();
        this.doorKey = doorKey;
    }

    public boolean matchKey(Key key) {
        return key.equals(this.doorKey);
    }

    //TODO : match key with current door
    // TODO: possibility to lock and unlock door
}
