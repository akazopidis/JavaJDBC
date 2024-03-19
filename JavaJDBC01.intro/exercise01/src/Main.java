import java.sql.*;

public class Main {

    public static void print_country(ResultSet row) throws SQLException {
        System.out.println("Code: " + row.getString("Code"));
        System.out.println("Name: " + row.getString("Name"));
        System.out.println("Continent: " + row.getString("Continent"));
        System.out.println("Region: " + row.getString("Region"));
        System.out.println("Continent: " + row.getBigDecimal("SurfaceArea"));
        System.out.println("Population: " + row.getInt("Population"));
        System.out.println("Life Expectancy: " + row.getBigDecimal("LifeExpectancy"));
        System.out.println("GNP: " + row.getBigDecimal("GNP"));
        System.out.println("GNP(old): " + row.getBigDecimal("GNPOld"));
        System.out.println("Head of State: " + row.getString("HeadOfState"));
        System.out.println("Capital: " + row.getInt("Capital"));
        System.out.println("Code2: " + row.getString("Code2"));
        System.out.println("=".repeat(20));
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javaworld",
                "javauser", "java1234")) {

            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM country";

            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                print_country(result);
            }

            stmt.close();
            result.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
