package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.GameBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameBoardWrapper extends SqliteWrapper {

    private void createTableGameBoards() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_GAME_BOARDS +
                "(" + ApplicationConstants.TABLE_GAME_BOARDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_GAME_BOARDS_NAME_COLUMN + " TEXT NOT NULL," +
                ApplicationConstants.TABLE_GAME_BOARDS_SIZE_COLUMN + " INTEGER NOT NULL" +
                ");";
        createTable(sql);
    }

    public void insert(GameBoard gameBoard) {
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

    @Override
    public void createTable() {
        createTableGameBoards();
    }
}
