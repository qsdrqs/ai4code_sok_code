import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDB {
    private static Connection db;

    public static void main(String[] args) {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:studentdb");

            Statement stmt = db.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
            stmt.close();

            insert("Foo", 42);
            insert("Bar", 55);

            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String name, int age) {
        try {
            PreparedStatement stmt = db.prepareStatement("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)");
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            stmt.finalize();

            /*try (Statement selectStmt = db.createStatement();
                 ResultSet rs = selectStmt.executeQuery("SELECT * FROM STUDENTS")) {
                while (rs.next()) {
                    System.out.println("NAME: " + rs.getString("NAME") + ", AGE: " + rs.getInt("AGE"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }*/
        } catch (SQLException | Throwable e) {
            e.printStackTrace();
        }
    }
}