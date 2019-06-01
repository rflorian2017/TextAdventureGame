package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.Player;
import game.model.gamedata.GameBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerWrapper extends SqliteWrapper {

    public void delete() {
        String sql = "DELETE * FROM " + ApplicationConstants.TABLE_PLAYERS ;

        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(Player player, int gameBoardId) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_PLAYERS +
                "(" + ApplicationConstants.TABLE_PLAYER_NAME_COLUMN + ","
                + ApplicationConstants.TABLE_PLAYER_ID_COLUMN + "," +
                ApplicationConstants.TABLE_PLAYER_HORIZONTAL + "," +
                ApplicationConstants.TABLE_PLAYER_VERTICAL + "," +
                ApplicationConstants.TABLE_PLAYER_GAMEBOARD_ID +
                ")" +
                " VALUES(?,?,?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, player.getName());
            statement.setInt(2, player.getID());
            statement.setInt(3, player.getHorizontal());
            statement.setInt(4, player.getVertical());
            statement.setInt(5, gameBoardId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTablePlayers() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_PLAYERS +
                "(" +
                ApplicationConstants.TABLE_PLAYER_ID_COLUMN + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_PLAYER_GAMEBOARD_ID + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_PLAYER_HORIZONTAL + " INTEGER NOT NULL," +
                ApplicationConstants.TABLE_PLAYER_VERTICAL + " INTEGER NOT NULL," +
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
                Player player = new Player(resultSet.getString(ApplicationConstants.TABLE_PLAYER_NAME_COLUMN),
                        resultSet.getInt(ApplicationConstants.TABLE_PLAYER_ID_COLUMN));
                player.setPosition(resultSet.getInt(ApplicationConstants.TABLE_PLAYER_HORIZONTAL),
                        resultSet.getInt(ApplicationConstants.TABLE_PLAYER_VERTICAL));
                players.add(player
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public void createTable() {
        createTablePlayers();
    }
}
