package game.model;

import java.util.List;

public class Artifact {

    private List<String > commands;
    private static int id;
    protected int id_new;

    public Artifact(){
        id_new = id;
        id++;
    }

    public int getId() {
        return id_new;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
