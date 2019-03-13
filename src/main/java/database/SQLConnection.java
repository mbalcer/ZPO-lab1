package database;

import java.sql.*;

public class SQLConnection {
    private Connection connect = null;
    private PreparedStatement stmt = null;

    private static final String DB_NAME = "company";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public SQLConnection() {
        dbConnection();
    }

    private void dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet makeQuery(String query) {
        ResultSet rs = null;
        try {
            this.stmt = this.connect.prepareStatement(query);
            rs = this.stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeConnect(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnect() {
        return connect;
    }
}
