import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Connection db;

    public static void main(String[] args) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite::memory:");

        addNewEntry("XXXXX", 0);
        addNewEntry("XXXXX", 0);

        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT NAME, AGE FROM STUDENTS")) {
            while (rs.next()) {
                System.out.println("{ NAME: '" + rs.getString("NAME") + "', AGE: " + rs.getInt("AGE") + " }");
            }
        }

        db.close();
    }

    public static void addNewEntry(String name, int age) throws SQLException {
        try (Statement stmt = db.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)");
        }

        try (PreparedStatement pstmt = db.prepareStatement("INSERT INTO STUDENTS VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}