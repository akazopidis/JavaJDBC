import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            // insert via ResultSet
            CallableStatement stmt = conn.prepareCall("CALL country_cities(?)");

            stmt.setString(1, "Greece");

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("name") + " " +
                        result.getString("district") + " " +
                        result.getLong("population"));
            }

            result.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
