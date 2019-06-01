package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.gamedata.Game;
import game.model.gamedata.GameBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectedGameBoardsWrapper extends SqliteWrapper {

    private void createTableRelated() {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                ApplicationConstants.TABLE_RELATED_BOARDS + "(" +
                ApplicationConstants.TABLE_RELATED_BOARDS_ID + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_RELATED_BOARDS_MAP + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_RELATED_HORIZONTAL_CONNECTION + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_RELATED_VERTICAL_CONNECTION + " INTEGER NOT NULL" +
                ");";
        createTable(sql);
    }

    @Override
    public void createTable() {
        createTableRelated();
    }


    public void delete() {
        String sql = "DELETE * FROM " +  ApplicationConstants.TABLE_RELATED_BOARDS ;
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertRelatedBoards(GameBoard gameBoard) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_RELATED_BOARDS +
                "(" + ApplicationConstants.TABLE_RELATED_BOARDS_ID + "," +
                ApplicationConstants.TABLE_RELATED_BOARDS_MAP + "," +
                ApplicationConstants.TABLE_RELATED_HORIZONTAL_CONNECTION + "," +
                ApplicationConstants.TABLE_RELATED_VERTICAL_CONNECTION
                + ")" +
                "VALUES(?,?,?,?);";
        for (Map.Entry<GameBoard, List<Integer>> gameBoardListEntry :
                gameBoard.getConnectedGameBoards().entrySet()
        ) {

            try {
                Connection conn = this.connect();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, gameBoard.getUniqueId());
                statement.setInt(2, gameBoardListEntry.getKey().getUniqueId());
                statement.setInt(3, gameBoardListEntry.getValue().get(0));
                statement.setInt(4, gameBoardListEntry.getValue().get(1));

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Integer, List<Integer>> getAllRelatedGameBoards() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_RELATED_BOARDS;
        Map<Integer, List<Integer>> relatedBoards = new HashMap<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                List<Integer> connectedBoardInfo = new ArrayList<>();
                connectedBoardInfo.add(resultSet.getInt(ApplicationConstants.TABLE_RELATED_BOARDS_MAP));
                connectedBoardInfo.add(resultSet.getInt(ApplicationConstants.TABLE_RELATED_HORIZONTAL_CONNECTION));
                connectedBoardInfo.add(resultSet.getInt(ApplicationConstants.TABLE_RELATED_VERTICAL_CONNECTION));

                relatedBoards.put(resultSet.getInt(ApplicationConstants.TABLE_RELATED_BOARDS_ID),
                        connectedBoardInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatedBoards;
    }
}
