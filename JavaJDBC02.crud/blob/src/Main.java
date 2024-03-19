import java.io.*;
import java.sql.*;

public class Main {


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaworld", "javauser", "java1234")) {

            File f = new File("MySQL.png");
            File f2 = new File("MySQL_copy.png");
            byte[] image;

            try (BufferedInputStream bs = new BufferedInputStream(
                    new FileInputStream(f));
                 BufferedOutputStream bos = new BufferedOutputStream(
                         new FileOutputStream(f2))) {

                // 1. read the image (from file)
                image = bs.readAllBytes();

                // 2. write the image (to db)
                String query = "INSERT INTO images(image, descr) " +
                        " VALUES(?, ?)";

                PreparedStatement stmt = conn.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);

                // stmt.setBlob(1, new SerialBlob(image));
                stmt.setBytes(1, image);
                stmt.setString(2, "My SQL Logo");

                stmt.executeUpdate();

                ResultSet genKeys = stmt.getGeneratedKeys();
                genKeys.next();
                long id = genKeys.getLong(1);
                System.out.println("id = " + id);
                stmt.close();

                // 3. read image from db
                Statement stmtRead = conn.createStatement();
                ResultSet result = stmtRead.executeQuery("SELECT * FROM images" +
                        " WHERE image_id = " + id);
                result.next();
                System.out.println(result.getString("descr"));
                image = result.getBytes("image");

                // 4. save to file
                bos.write(image);

            }
            catch (FileNotFoundException e) {
                System.err.println("File not found");
            }
            catch (IOException e) {
                System.err.println("I/O error");
            }



        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}

