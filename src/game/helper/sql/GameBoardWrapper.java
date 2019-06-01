package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.gamedata.GameBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoardWrapper extends SqliteWrapper {

    private void createTableGameBoards() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" + ApplicationConstants.TABLE_GAME_BOARDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + " TEXT NOT NULL," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + " INTEGER NOT NULL" +
                ");";
        createTable(sql);
    }

    public void delete() {
        String sql = "DELETE * FROM " + ApplicationConstants.TABLE_GAME_BOARDS ;

        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(GameBoard gameBoard) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" +
                ApplicationConstants.TABLE_GAME_BOARDS_ID_COLUMN + "," +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + "," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + ")" +
                "VALUES(?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, gameBoard.getUniqueId());
            statement.setString(2, gameBoard.getName());
            statement.setInt(3, gameBoard.getBoardSize());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createTable() {
        createTableGameBoards();
    }

    public List<GameBoard> getAllGameBoards() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_GAME_BOARDS;
        List<GameBoard> gameBoards = new ArrayList<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                gameBoards.add(new GameBoard(
                        resultSet.getString(ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN),
                        resultSet.getInt(ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameBoards;
    }
}
