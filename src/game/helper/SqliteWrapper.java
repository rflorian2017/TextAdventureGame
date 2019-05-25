package game.helper;


import game.constants.ApplicationConstants;
import game.model.GameBoard;


import java.sql.*;

public class SqliteWrapper {

    private Connection connect() {
        // connect to the database
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(ApplicationConstants.DB_CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

    private void createTable(String sql) {
        try {
            Connection conn = this.connect();
            // the query I want to feed the sql query to
            Statement statement = conn.createStatement();
            // executed on the db defined by the connection
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTablePlayers() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_PLAYERS +
                "(" +
                ApplicationConstants.TABLE_PLAYERS_USERNAME_COLUMN + " TEXT UNIQUE NOT NULL, " +
                ApplicationConstants.TABLE_PLAYERS_PASSWORD_COLUMN + " TEXT NOT NULL" +
                ");";
        createTable(sql);
    }

    private void createTableGameBoards() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" + ApplicationConstants.TABLE_GAME_BOARDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + " TEXT NOT NULL," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + " INTEGER NOT NULL" +
                ");";
        createTable(sql);
    }

    private void createTableWords() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_WORDS +
                "(" + ApplicationConstants.TABLE_WORDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_WORDS_NAME_COLUMN + " TEXT UNIQUE NOT NULL," +
                ApplicationConstants.TABLE_WORDS_HINT_COLUMN + " TEXT," +
                ApplicationConstants.TABLE_WORDS_CATEGORY_ID_COLUMN + " INTEGER NOT NULL" +
                ")";
        createTable(sql);
    }

    public void insertGameBoard(GameBoard gameBoard) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + "," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + ")" +
                "VALUES(?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, gameBoard.getName());
            statement.setInt(2, gameBoard.getBoardSize());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createAllTables() {
        //createTablePlayers();
        createTableGameBoards();
    }

}
