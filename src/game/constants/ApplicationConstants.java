package game.constants;

public class ApplicationConstants {
//    public static final String BTN_LOGIN_TEXT = "Login";
//    public static final String BTN_LOGOUT_TEXT = "Logout";
//    public static final String BTN_REGISTER_PLAYER = "Player login";
//    public static final String APP_USERNAME = "admin";
//    public static final String APP_PASSWORD = "admin";

    public static final String APP_FOLDER_DATA_PATH = "c:\\Users\\Public\\Documents\\textadventure";

    public static final String PATH_SEPARATOR = "\\"; //windows style

    // region Database

    public static final String DATABASE_NAME = "textadventure.db";
    public static final String DATABASE_FOLDER = "database";
    public static final String SQLITE_JDBC = "jdbc:sqlite";
    //jdbc:sqlite:c:\Users\Public\Documents\hangman\hangman.db
    public static final String DB_CONNECTION_URL =
            SQLITE_JDBC +
                    ":" +
                    APP_FOLDER_DATA_PATH +
                    PATH_SEPARATOR +
                    DATABASE_FOLDER +
                    PATH_SEPARATOR +
                    DATABASE_NAME;


    //region table artifacts
    public static final String TABLE_GAME_ARTIFACTS="game_artifacts";
    public static final String TABLE_GAME_ARTIFACTS_ID_COLUMN="id";
    public static final String TABLE_GAME_ARTIFACTS_NAME_COLUMN="name";
    public static final String TABLE_GAME_ARTIFACTS_COLLECTIBLE_COLUMN="collectible";

    //endregion

    //region table artifacts_position
    public static final String TABLE_ARTIFACTS_POSITION="artifacts_position";
    public static final String TABLE_ARTIFACTS_HORIZONTAL_POSITION="horizontal";
    public static final String TABLE_ARTIFACTS_VERTICAL_POSITION="vertical";
    public static final String TABLE_ARTIFACTS_ID = "artifact_id";
    public static final String TABLE_ARTIFACTS_GAMEBOARD_ID="gameboard_id";
    //endregion

    // region table Game boards
    public static final String TABLE_GAME_BOARDS = "game_boards";
    public static final String TABLE_GAME_BOARDS_ID_COLUMN = "id";
    public static final String TABLE_GAME_BOARDS_NAME_COLUMN = "name";
    public static final String TABLE_GAME_BOARDS_SIZE_COLUMN = "board_size";
    // endregion

    // region table player
    public static final String TABLE_PLAYER_NAME = "players";
    public static final String TABLE_PLAYER_ID_COLUMN = "id";
    public static final String TABLE_PLAYER_NAME_COLUMN = "name";
    public static final String TABLE_PLAYERS = "players";
    public static final String TABLE_PLAYERS_NAME_COLUMN = "name";

    // endregion



}
