package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerWrapper extends SqliteWrapper {

    public void insert(Player player) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_PLAYERS +
                "(" + ApplicationConstants.TABLE_PLAYERS_NAME_COLUMN + "," + ApplicationConstants.TABLE_PLAYER_ID_COLUMN +
                ")" +
                " VALUES(?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, player.getName());
            statement.setInt(2, player.getID());
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
                players.add(new Player(resultSet.getString(ApplicationConstants.TABLE_PLAYER_NAME_COLUMN),
                        resultSet.getInt(ApplicationConstants.TABLE_PLAYER_ID_COLUMN)
                ));
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
