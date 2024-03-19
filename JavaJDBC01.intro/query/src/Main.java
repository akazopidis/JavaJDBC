import java.sql.*;

public class Main {

    public static void main(String[] args)  {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM country";

            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                System.out.print(result.getString("Name") + ", ");
                System.out.println(result.getInt("Population"));
            }

            stmt.close();
            result.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }
}
