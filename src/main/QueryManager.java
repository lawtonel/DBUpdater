package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

public class QueryManager extends Observable {
    private static java.sql.Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int remainingPlaces;
    private ArrayList<String> coursesInClearing;

    public QueryManager() {
        coursesInClearing = getAllCourses();
    }

    public ArrayList<String> getAllCourses() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emily", "Gander41");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT UCASid " +
                    "FROM Programme");
            coursesInClearing = new ArrayList<>();
            while(resultSet.next()) {
                coursesInClearing.add(resultSet.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return coursesInClearing;
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void decreaseNoPlaces(String UCASCode) {

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
                notifyObservers();
                connection.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public ArrayList<String> getCoursesInClearing() {
        return coursesInClearing;
    }

    public int getRemainingPlaces() {
        return remainingPlaces;
    }
}


