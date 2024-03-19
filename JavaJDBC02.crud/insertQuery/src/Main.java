import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            // insert via prepareStatement
            String query = "INSERT INTO city(Name, countryCode, District, Population) " +
                    " VALUES('Chania','GRC', 'Crete', 100000)";

            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            genKeys.next();
            System.out.println("id = " + genKeys.getLong(1));
            stmt.close();


            // insert via ResultSet
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet result = stmt2.executeQuery("SELECT * FROM city");
            result.moveToInsertRow();
            result.updateString("Name", "Rethymno"); // insert times
            result.updateString("CountryCode", "GRC");
            result.updateString("District", "Crete");
            result.updateLong("Population", 50000L);
            result.insertRow();
            result.last();
            System.out.println("id = " + result.getInt("ID"));

            result.close();
            stmt2.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
