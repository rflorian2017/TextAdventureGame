package game.helper.sql;

import game.constants.ApplicationConstants;
import game.model.Artifact;
import game.model.gamedata.GameBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtifactPositionWrapper extends SqliteWrapper {
    private void createArtifactsPosition() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_ARTIFACTS_POSITION +
                "("
                + ApplicationConstants.TABLE_ARTIFACTS_ID + " INTEGER NOT NULL, "
                + ApplicationConstants.TABLE_ARTIFACTS_HORIZONTAL_POSITION + " INTEGER NOT NULL,"
                + ApplicationConstants.TABLE_ARTIFACTS_VERTICAL_POSITION + " INTEGER NOT NULL,"
                + ApplicationConstants.TABLE_ARTIFACTS_GAMEBOARD_ID + " INTEGER NOT NULL);";
        createTable(sql);
    }

    public void insert(Artifact artifact, GameBoard gameBoard) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_ARTIFACTS_POSITION +
                "("
                + ApplicationConstants.TABLE_ARTIFACTS_ID + "," +
                ApplicationConstants.TABLE_ARTIFACTS_HORIZONTAL_POSITION + "," +
                ApplicationConstants.TABLE_ARTIFACTS_VERTICAL_POSITION + "," +
                ApplicationConstants.TABLE_ARTIFACTS_GAMEBOARD_ID + ")" +
                " VALUES(?,?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, artifact.getId());
            statement.setInt(2, gameBoard.artifactVerticalPosition(artifact));
            statement.setInt(3, gameBoard.artifactHorizontalPosition(artifact));
            statement.setInt(4, gameBoard.getUniqueId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, List<Integer>> getAllArtifactsPositions() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_ARTIFACTS_POSITION ;
        List<Artifact> artifacts = new ArrayList<>();
        List<GameBoard> gameBoards = new ArrayList<>();
        Map<Integer, List<Integer>> artifactsPositions = new HashMap<>();

        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                List<Integer> coordinates = new ArrayList<>();
                coordinates.add(resultSet.getInt(ApplicationConstants.TABLE_ARTIFACTS_HORIZONTAL_POSITION));
                coordinates.add(resultSet.getInt(ApplicationConstants.TABLE_ARTIFACTS_VERTICAL_POSITION));

                artifactsPositions.put(resultSet.getInt(ApplicationConstants.TABLE_ARTIFACTS_ID ), coordinates);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifactsPositions;
    }

    @Override
    public void createTable() {
        createArtifactsPosition();
    }
}
