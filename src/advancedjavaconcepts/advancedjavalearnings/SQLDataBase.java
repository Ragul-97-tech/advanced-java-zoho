package advancedjavaconcepts.advancedjavalearnings;

import java.sql.*;

public class SQLDataBase {
    public static void main(String[] args) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Country", "root", "Kasiragul97");
            String query = "SELECT * FROM Countries";
            Statement state = connect.createStatement();
            ResultSet resultSet = state.executeQuery(query);
//            System.out.printf("Country Name:  Country Code: %4-s Capital Name: %16-s");
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String countryName = resultSet.getString("countryName");
                String countryCode = resultSet.getString("countryCode");
                String capitalName = resultSet.getString("capitalName");
                System.out.printf("%-25s | %-4s | %-16s %n", countryName, countryCode, capitalName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
