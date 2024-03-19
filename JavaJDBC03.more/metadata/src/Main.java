import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            DatabaseMetaData metaData = conn.getMetaData();

            // DBMS + driver info
            System.out.println("DBMS name: " + metaData.getDatabaseProductName());
            System.out.println("DBMS version: " + metaData.getDatabaseProductVersion());
            System.out.println("DBMS major version: " + metaData.getDatabaseMajorVersion());
            System.out.println("DBMS minor version: " + metaData.getDatabaseMinorVersion());

            // DB tables
            ResultSet resultSet =
                    metaData.getTables(null, null, null, null);
            // or metaData.getTables(null, null, null, new String[] {"TABLE"});
            System.out.print("\n\nTables: ");
            while(resultSet.next()) {
                System.out.println();
                for (int i=1; i<=4; i++)
                    System.out.print(resultSet.getString(i) + " ");
            }

            // table columns:
            ResultSet columns = metaData.getColumns(null,null, "country", null);
            System.out.println("\n\nTable Columns: ");
            while(columns.next()) {
                System.out.print("Column: " + columns.getString("COLUMN_NAME") +
                        " " + columns.getString("DATA_TYPE") +
                        " NULL:" + columns.getString("IS_NULLABLE") +
                        " AUTOINC:" + columns.getString("IS_AUTOINCREMENT") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}