import java.sql.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO city(Name, CountryCode, District, Population)" +
                            " VALUES(?, ?, ?, ?)");
            statement.setString(1, "Chania");
            statement.setString(2, "GRC");
            statement.setString(3, "Crete");
            statement.setLong(4, 100000);
            statement.addBatch();
            statement.setString(1, "Rethymno");
            statement.setLong(4, 50000);
            statement.addBatch();
            System.out.println(Arrays.toString(statement.executeBatch()));

            statement.close();
      }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}

