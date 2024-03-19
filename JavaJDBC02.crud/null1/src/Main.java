
import java.sql.*;

public class Main {


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            /*
             //Insertion problems with nullable column
            PreparedStatement pr = conn.prepareStatement(
                    "INSERT INTO country " +
                            " (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LocalName, GovernmentForm, Code2) " +
                            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            pr.setString(1, "WAK");
            pr.setString(2, "Wakanda");
            pr.setString(3, "Africa");
            pr.setString(4, "Wakaha");
            pr.setDouble(5, 1234.012);
            Integer x = null;
            pr.setInt(6, x);
            pr.setLong(7, 234);
            pr.setString(8, "WAK");
            pr.setString(9, "WAK");
            pr.setString(10, "WAK");
            pr.executeUpdate();

             */

            Statement stmtRead = conn.createStatement();
            ResultSet result = stmtRead.executeQuery(
                    "SELECT IndepYear " +
                            " FROM country " +
                            " WHERE Name='Aruba'");
            result.next();
            Integer year = result.getInt("IndepYear");
            System.out.println(year);
            if (result.wasNull())
                year = null;
            System.out.println(year);

            stmtRead.close();
            result.close();


        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
