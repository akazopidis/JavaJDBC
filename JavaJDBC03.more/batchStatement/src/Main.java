import java.sql.*;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            Statement statement = conn.createStatement();
            statement.addBatch("INSERT INTO city(Name, CountryCode, District, Population)" +
                    " VALUES('Chania', 'GRC', 'Crete', 100000)");
            statement.addBatch("INSERT INTO city(Name, CountryCode, District, Population)" +
                    " VALUES('Rethymno', 'GRC', 'Crete', 50000)");
            System.out.println(Arrays.toString(statement.executeBatch()));

            statement.close();
      }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}

