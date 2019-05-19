package game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameStrategy {
    private static HashMap<String, List<String>> commandsMap; // e. g. move ; [east, west, north, south, up, down, right, left]

    static {
        commandsMap = new HashMap<>();
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
        if (commandsMap.containsKey(commands[0].toLowerCase())) {
            //game.movePlayer()
            switch (commands[1].toLowerCase()) {
                case "east":
                case "right":
                case "e":
                case "r":
                    game.movePlayer(player, player.getHorizontal(),
                            player.getVertical() + 1);
                    break;
                case "west":
                case "left":
                case "w":
                case "l":
                    game.movePlayer(player, player.getHorizontal(), player.getVertical() - 1);
                    break;
                case "north":
                case "up":
                case "n":
                case "u":
                    game.movePlayer(player, player.getHorizontal() - 1, player.getVertical());
                    break;
                case "south":
                case "down":
                case "s":
                case "d":
                    game.movePlayer(player, player.getHorizontal() + 1, player.getVertical() - 1);
                    break;

            }
        }

    }
}
