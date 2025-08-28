import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry
 *
 * @param name string for the "NAME" of the entry
 * @param age int for the "AGE" of the entry
 * @param db a given database connection
 */
public static void addEntry(String name, int age, Connection db) {
    String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    try (PreparedStatement stmt = db.prepareStatement(sql)) {
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Or handle the exception as appropriate for your application
    }
}