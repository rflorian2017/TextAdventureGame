package game.model;

public class GameStrategy {
    public static void processCommand(String command, Game game, Player player) {
        String[] commands = command.split(" ");
        //e.g move 2 5 -> commands[0] = move, commands[1] = 2, commands[2] = 5
        if(commands[0].equals("move")) {
            game.movePlayer(player ,
                    Integer.parseInt(commands[1]),
                    Integer.parseInt(commands[2]));
        }
    }
}
