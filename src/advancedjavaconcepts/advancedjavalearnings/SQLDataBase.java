package advancedjavaconcepts.advancedjavalearnings;

import java.sql.*;
import java.util.Scanner;

public class SQLDataBase {
    public static void main(String[] args) {
        Scanner userInputs = new Scanner(System.in);
        System.out.print("Enter DataBase Name: ");
        String DBName = userInputs.nextLine();
        System.out.println(DBName);
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName.trim(), "root", "Kasiragul97");
            String query = "SELECT * FROM ?";
            Statement state = connect.createStatement();
            ResultSet resultSet = state.executeQuery(query);
            ResultSetMetaData metaDate = resultSet.getMetaData();
            System.out.println(metaDate.getColumnCount());
//            System.out.printf("Country Name:  Country Code: %4-s Capital Name: %16-s");
            while (resultSet.next()) {
                int hobbyId = resultSet.getInt("hobbyId");
                String hobbyName = resultSet.getString("hobbyName");
                Category category =  Category.valueOf(resultSet.getString("category"));
                String description = resultSet.getString("description");
                System.out.printf("%-20s | %-8s | %-16s %n", hobbyName, category, description);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

enum Category {
    Outdoor, Indoor
}
