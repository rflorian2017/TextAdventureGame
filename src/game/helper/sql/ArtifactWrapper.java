package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.Artifact;
import game.model.CollectibleItem;
import game.model.Door;
import game.model.Key;
import game.model.factory.ArtifactFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtifactWrapper extends SqliteWrapper {
    private void createTableArtifacts() {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                ApplicationConstants.TABLE_GAME_ARTIFACTS +
                "(" + ApplicationConstants.TABLE_GAME_ARTIFACTS_ID_COLUMN + " INTEGER UNIQUE NOT NULL, " +

                ApplicationConstants.TABLE_GAME_ARTIFACTS_NAME_COLUMN + " TEXT NOT NULL, " +
                ApplicationConstants.TABLE_GAME_ARTIFACTS_COLLECTIBLE_COLUMN + " INTEGER NOT NULL " +

                ");";
        createTable(sql);
    }

    public void insert(Artifact artifact) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_GAME_ARTIFACTS +
                "(" + ApplicationConstants.TABLE_GAME_ARTIFACTS_ID_COLUMN + ","
                + ApplicationConstants.TABLE_GAME_ARTIFACTS_NAME_COLUMN + ","
                + ApplicationConstants.TABLE_GAME_ARTIFACTS_COLLECTIBLE_COLUMN + ")" +
                "VALUES(?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, artifact.getId());
            statement.setString(2, artifact.getName());
            statement.setInt(3, (artifact instanceof CollectibleItem) ? 1 : 0);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Artifact> getAllArtifacts() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_GAME_ARTIFACTS;
        List<Artifact> artifacts = new ArrayList<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String artifactName = resultSet.getString(ApplicationConstants.TABLE_GAME_ARTIFACTS_NAME_COLUMN);

                artifacts.add(ArtifactFactory.createArtifact(artifactName,
                        resultSet.getInt(ApplicationConstants.TABLE_GAME_ARTIFACTS_ID_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts;
    }

    @Override
    public void createTable() {
        createTableArtifacts();
    }
}
