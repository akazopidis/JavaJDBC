import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            System.out.println("statement insert. Press Enter to continue...");
            sc.nextLine();
            // insert via prepareStatement
            String query = "INSERT INTO city(Name, countryCode, District, Population) " +
                    " VALUES('Chania','GRC', 'Crete', 100000)";

            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            genKeys.next();
            long id1 = genKeys.getLong(1);
            stmt.close();


            System.out.println("result set insert. Press Enter to continue...");
            sc.nextLine();
            // insert via ResultSet
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet result = stmt2.executeQuery("SELECT * FROM city");
            result.moveToInsertRow();
            result.updateString("Name", "Rethymno");
            result.updateString("CountryCode", "GRC");
            result.updateString("District", "Crete");
            result.updateLong("Population", 50000L);
            result.insertRow();
            result.last();
            long id2 = result.getInt("ID");

            result.close();
            stmt2.close();


            System.out.println("statement update. Press Enter to continue...");
            sc.nextLine();
            // update via PreparedStatement
            String updQuery = "UPDATE city " +
                    " SET Name = ?" +
                    " WHERE ID = ?";

            PreparedStatement updStmt = conn.prepareStatement(updQuery);
            updStmt.setString(1, "Ag. Nikolaos");
            updStmt.setLong(2, id1);
            updStmt.executeUpdate();

            updStmt.close();

            System.out.println("Result Set update. Press Enter to continue...");
            sc.nextLine();
            // update via ResultSet
            Statement stmtUpdate2 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultUpd = stmtUpdate2.executeQuery(
                    "SELECT * FROM city" +
                    " WHERE ID=" + id2);
            resultUpd.next();
            resultUpd.updateString("Name", "Ierapetra");
            resultUpd.updateRow();

            resultUpd.close();
            stmtUpdate2.close();


            System.out.println("Statement delete. Press Enter to continue...");
            sc.nextLine();
            // delete via PreparedStatement
            String delQuery = "DELETE FROM city " +
                    " WHERE ID = ?";
            PreparedStatement delStmt = conn.prepareStatement(delQuery);
            delStmt.setLong(1, id1);
            delStmt.executeUpdate();

            delStmt.close();

            System.out.println("ResultSet delete. Press Enter to continue...");
            sc.nextLine();
            // delete via ResultSet
            Statement stmtDel2 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultDel = stmtDel2.executeQuery(
                    "SELECT * FROM city" +
                            " WHERE ID=" + id2);
            resultDel.next();
            resultDel.deleteRow();

            resultDel.close();
            stmtDel2.close();


            System.out.println("Done!");
            sc.next();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
