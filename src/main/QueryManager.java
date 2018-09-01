package main;

import java.sql.*;

public class QueryManager {
    private static java.sql.Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int remainingPlaces;

    public QueryManager() {
    }

    public int retrieveRemainingPlaces(String UCASCode) {
        String UCASCodeUpCase = UCASCode.toUpperCase();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emily", "Gander41");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT remainingPlaces " +
                        "FROM Programme " +
                        "WHERE UCASid = '" + UCASCodeUpCase + "'");
            resultSet.next();
            remainingPlaces = resultSet.getInt(1);
            connection.close();
            return remainingPlaces;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int decreaseNoPlaces(String UCASCode) {
        String UCASCodeUpCase = UCASCode.toUpperCase();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emily", "Gander41");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "UPDATE Programme " +
                            "SET remainingPlaces = remainingPlaces - 1 " +
                            "WHERE UCASid = '" + UCASCodeUpCase + "'");
            retrieveRemainingPlaces(UCASCode);
            connection.close();
            return remainingPlaces;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}


