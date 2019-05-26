package game.helper.sql;


import game.constants.ApplicationConstants;
import java.sql.*;

public abstract class SqliteWrapper {

    //TODO : update statement, when the record already exists: e.g. UPDATE WHERE ID = ?

    protected Connection connect() {
        // connect to the database
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(ApplicationConstants.DB_CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

    protected void createTable(String sql) {
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





    public abstract void createTable() ;
    public void insert() {

    }

}
