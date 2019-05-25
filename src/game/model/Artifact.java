package game.model;

import java.util.List;

public class Artifact {

    private List<String > commands;
    private static int id;

    public Artifact(){
        id++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
