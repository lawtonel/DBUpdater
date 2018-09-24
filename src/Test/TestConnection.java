package Test;

import java.sql.*;

public class TestConnection {

    //public static java.sql.main.QueryManager getConnection() {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "emily", "Gander41");
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //return null;
    }
}
