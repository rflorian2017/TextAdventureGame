package game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameStrategy {
    private static HashMap<String, List<String>> commandsMap; // e. g. move ; [east, west, north, south, up, down, right, left]

    static {
        commandsMap.put("move", new ArrayList<>());
        List<String> possibleValuesForMove = new ArrayList<>();
        possibleValuesForMove.add("east");
        possibleValuesForMove.add("west");
        possibleValuesForMove.add("south");
        possibleValuesForMove.add("north");
        possibleValuesForMove.add("right");
        possibleValuesForMove.add("left");
        possibleValuesForMove.add("up");
        possibleValuesForMove.add("down");
        possibleValuesForMove.add("e");
        possibleValuesForMove.add("w");
        possibleValuesForMove.add("n");
        possibleValuesForMove.add("s");
        possibleValuesForMove.add("r");
        possibleValuesForMove.add("l");
        possibleValuesForMove.add("u");
        possibleValuesForMove.add("d");
        commandsMap.get("move").addAll(possibleValuesForMove);
        commandsMap.put("go", possibleValuesForMove);
    }

    public static void processCommand(String command, Game game, Player player) {

        String[] commands = command.split(" ");
        if(commandsMap.containsKey(commands[0])) {
            //game.movePlayer()
        }
        //e.g move 2 5 -> commandsMap[0] = move, commandsMap[1] = 2, commandsMap[2] = 5
        if(commands[0].equals("move")) {
            game.movePlayer(player ,
                    Integer.parseInt(commands[1]),
                    Integer.parseInt(commands[2]));
        }
    }
}
