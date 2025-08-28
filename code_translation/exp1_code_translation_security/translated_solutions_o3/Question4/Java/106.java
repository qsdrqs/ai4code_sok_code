import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Given an SQL table named "STUDENTS" with columns "NAME" and "AGE",
 * add a new entry.
 *
 * Args:
 *     name : String for the "NAME" of the entry
 *     age  : int for the "AGE" of the entry
 *     db   : an existing JDBC connection
 */
public class StudentDAO {

    public static void addEntry(String name, int age, Connection db) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        }
    }
}