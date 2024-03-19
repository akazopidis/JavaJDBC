
import java.sql.*;

public class Main {


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            // select
            Statement stmtRead = conn.createStatement();
            ResultSet result = stmtRead.executeQuery(
                    "SELECT IndepYear " +
                            " FROM country " +
                            " WHERE Name='Aruba' OR Name='Afghanistan'");
            result.next();
            Integer year = (Integer)result.getObject("IndepYear");
            System.out.println(year);
            result.next();
            year = (Integer)result.getObject("IndepYear");
            System.out.println(year);

            stmtRead.close();
            result.close();


            // insert
            PreparedStatement pr = conn.prepareStatement(
                    "INSERT INTO country " +
                            " (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LocalName, GovernmentForm, Code2) " +
                            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            pr.setString(1, "WAK");
            pr.setString(2, "Wakanda");
            pr.setString(3, "Africa");
            pr.setString(4, "Wakaha");
            pr.setDouble(5, 1234.012);
            pr.setObject(6, null);
            pr.setLong(7, 234);
            pr.setString(8, "WAK");
            pr.setString(9, "WAK");
            pr.setString(10, "WA");
            pr.executeUpdate();


        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
