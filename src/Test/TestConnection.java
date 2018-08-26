package Test;

import java.sql.*;

public class TestConnection {
    private static java.sql.Connection connection;

    //public static java.sql.Connection getConnection() {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emily", "Gander41");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT remainingPlaces " +
                            "FROM Course " +
                            "WHERE UCASid = 'G400'");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
            connection.close();
            //return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return null;
    }
}
