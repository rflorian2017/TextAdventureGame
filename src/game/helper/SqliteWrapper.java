package game.helper;


import game.constants.ApplicationConstants;
import game.model.Artifact;
import game.model.CollectibleItem;
import game.model.GameBoard;
import game.model.Player;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private void createTableGameBoards() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" + ApplicationConstants.TABLE_GAME_BOARDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + " TEXT NOT NULL," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + " INTEGER NOT NULL" +
                ");";
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

    private void createTableArtifacts() {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                ApplicationConstants.TABLE_GAME_ARTIFACTS +
                "(" + ApplicationConstants.TABLE_GAME_ARTIFACTS_ID_COLUMN + " INTEGER UNIQUE NOT NULL, "+

                ApplicationConstants.TABLE_GAME_ARTIFACTS_NAME_COLUMN + " TEXT NOT NULL, " +
                ApplicationConstants.TABLE_GAME_ARTIFACTS_COLLECTIBLE_COLUMN + " INTEGER NOT NULL "+

                ");";
        createTable(sql);
    }

    public void insertArtifact(Artifact artifact) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_GAME_ARTIFACTS +
                "("+ ApplicationConstants.TABLE_GAME_ARTIFACTS_ID_COLUMN +","
                + ApplicationConstants.TABLE_GAME_ARTIFACTS_NAME_COLUMN +","
                +ApplicationConstants.TABLE_GAME_ARTIFACTS_COLLECTIBLE_COLUMN+ ")" +
                "VALUES(?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, artifact.getId());
            statement.setString(2,artifact.getClass().getName());
            statement.setInt(3,(artifact instanceof CollectibleItem)?1:0);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createArtifactsPosition(){
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_ARTIFACTS_POSITION +
                "("+
                ApplicationConstants.TABLE_ARTIFACTS_NAME + " TEXT NOT NULL,"
                + ApplicationConstants.TABLE_ARTIFACTS_ID + " INTEGER NOT NULL, "
                +  ApplicationConstants.TABLE_ARTIFACTS_HORIZONTAL_POSITION+ " INTEGER NOT NULL,"
                +ApplicationConstants.TABLE_ARTIFACTS_VERTICAL_POSITION +" INTEGER NOT NULL);"
                ;
        createTable(sql);
    }

    public void insertArtifactsPosition(Artifact artifact, GameBoard gameBoard) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_ARTIFACTS_POSITION +
                "(" + ApplicationConstants.TABLE_ARTIFACTS_NAME + ","
                + ApplicationConstants.TABLE_ARTIFACTS_ID + "," +
                ApplicationConstants.TABLE_ARTIFACTS_HORIZONTAL_POSITION + "," +
                ApplicationConstants.TABLE_ARTIFACTS_VERTICAL_POSITION+ ")" +
                " VALUES(?,?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, artifact.getName());
            statement.setInt(2, artifact.getId());
            statement.setInt(3, gameBoard.artifactVerticalPosition(artifact));
            statement.setInt(4, gameBoard.artifactHorizontalPosition(artifact));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertPlayer(Player player){
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_PLAYERS +
                "(" + ApplicationConstants.TABLE_PLAYERS_NAME_COLUMN + ","  + ApplicationConstants.TABLE_PLAYER_ID_COLUMN +
                ")" +
                " VALUES(?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,player.getName());
            statement.setInt(2,player.getID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTablePlayers() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_PLAYER_NAME +
                "(" +
                ApplicationConstants.TABLE_PLAYER_ID_COLUMN + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_PLAYER_NAME_COLUMN + " TEXT  NOT NULL" +
                ");";
        createTable(sql);
    }

    public List<Player> getAllPlayers() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_PLAYERS;
        List<Player> players = new ArrayList<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                players.add(new Player(resultSet.getString(ApplicationConstants.TABLE_PLAYER_NAME_COLUMN),
                        resultSet.getInt(ApplicationConstants.TABLE_PLAYER_ID_COLUMN)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public void createAllTables() {
        createTablePlayers();
        createTableGameBoards();
        createTableArtifacts();
        createArtifactsPosition();
    }

}
