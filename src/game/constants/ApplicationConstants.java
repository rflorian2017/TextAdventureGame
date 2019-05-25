package game.constants;

public class ApplicationConstants {
    public static final String BTN_LOGIN_TEXT = "Login";
    public static final String BTN_LOGOUT_TEXT = "Logout";
    public static final String BTN_REGISTER_PLAYER = "Player login";
    public static final String APP_USERNAME = "admin";
    public static final String APP_PASSWORD = "admin";

    public static final String APP_FOLDER_DATA_PATH = "c:\\Users\\Public\\Documents\\hangman";
    public static final String CATEGORIES_FOLDER_NAME = "categories";
    public static final String CATEGORY_FILE_EXTENSION = ".txt";
    public static final String WORD_SEPARATOR_IN_CATEGORY_ENTRY = ";";

    public static final String PLAYERS_FOLDER_NAME = "players";
    public static final String PATH_SEPARATOR = "\\"; //windows style

    public static final String PLAYER_LIST = "players.xml";

    // region Database

    public static final String DATABASE_NAME = "hangman.db";
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

    // region table players
    public static final String TABLE_PLAYERS = "players";
    public static final String TABLE_PLAYERS_USERNAME_COLUMN = "username";
    public static final String TABLE_PLAYERS_PASSWORD_COLUMN = "password";
    // endregion

    // region table categories
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_CATEGORIES_ID_COLUMN = "id";
    public static final String TABLE_CATEGORIES_NAME_COLUMN = "name";
    // endregion

    // region table words
    public static final String TABLE_WORDS = "words";
    public static final String TABLE_WORDS_ID_COLUMN = "id";
    public static final String TABLE_WORDS_NAME_COLUMN = "name";
    public static final String TABLE_WORDS_HINT_COLUMN = "hint";
    public static final String TABLE_WORDS_CATEGORY_ID_COLUMN = "category_id";
    // endregion

    // endregion

}
