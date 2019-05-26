package game.model.factory;

import game.model.Artifact;
import game.model.Door;
import game.model.Key;

public class ArtifactFactory {
    public static Artifact createArtifact(String className, int id) {
        Artifact artifact = null;
        if (className.equals(Key.class.getSimpleName())) {
            artifact = new Key();
        } else if (className.equals(Door.class.getSimpleName())) {
            artifact = new Door(null);
        }
        return artifact;
    }
}
