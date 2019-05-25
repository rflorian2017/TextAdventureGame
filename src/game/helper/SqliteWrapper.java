package game.helper;


import game.constants.ApplicationConstants;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    @Deprecated
    private String createTableCreationString(String table, HashMap<String, List<String>> columns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(";
        /* the hash map contains the column name as key and the properties as the values
        e.g.: Username, (TEXT, UNIQUE, NOT NULL) */
        // outer loop !!!!!
        for (String columnName : columns.keySet()) {
            sql += columnName + " ";
            for (String columnProperty : columns.get(columnName)) {
                sql += columnProperty + " ";
            }
            sql += ",";
        }
        //remove the last comma from the sql string, because we do not check in the outer for loop for the last element
        sql = sql.substring(0, sql.length() - 2); // -1 is the last character
        sql += ");";

        return sql;
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

    private void createTableCategories() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_CATEGORIES +
                "(" + ApplicationConstants.TABLE_CATEGORIES_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_CATEGORIES_NAME_COLUMN + " TEXT UNIQUE NOT NULL" +
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

    public void createAllTables() {
        createTablePlayers();
        createTableCategories();
        createTableWords();
    }

}
