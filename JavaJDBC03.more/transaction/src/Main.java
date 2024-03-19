import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            conn.setAutoCommit(false);

            try {
                String[] cities = {"Chania", "Rethymno"};
                int[] inc = {10000, 5000};

                // update city
                PreparedStatement stmtCity = conn.prepareStatement("UPDATE city SET Population = Population + ? WHERE Name = ?");
                for (int i = 0; i < cities.length; i++) {
                    stmtCity.setInt(1, inc[i]);
                    stmtCity.setString(2, cities[i]);
                    stmtCity.executeUpdate();
                }
                stmtCity.close();

                int sum = 0;
                for (var v : inc)
                    sum += v;

                // update country
                PreparedStatement stmtCountry = conn.prepareStatement("UPDATE country SET Population = Population + ? WHERE Name = 'Greece'");
                stmtCountry.setInt(1, sum);
                stmtCountry.executeUpdate();
                stmtCountry.close();

                conn.commit();
                System.out.println("Transaction complete");
            }
            catch (SQLException e) {
                System.out.println("Transaction failed");
                conn.rollback();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}