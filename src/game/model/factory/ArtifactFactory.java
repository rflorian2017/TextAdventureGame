package game.model.factory;

import game.model.Artifact;
import game.model.Door;
import game.model.Key;
import game.model.Tree;

public class ArtifactFactory {
    public static Artifact createArtifact(String className, int id) {
        Artifact artifact = null;
        if (className.equals(Key.class.getSimpleName())) {
            artifact = new Key();
        } else if (className.equals(Door.class.getSimpleName())) {
            artifact = new Door(null);
        }
        else if (className.equals(Tree.class.getSimpleName())) {
            artifact = new Tree();
        }
        return artifact;
    }
}
