import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDb {
    /**
     * db: Connection object (to SQLite), assumed to contain a STUDENTS (NAME, AGE) table
     * name: student name string
     * age: student age integer
     *
     * This function doesn't do any error checking on the types or sizes of `name`
     * and `age`, and neither does Sqlite, so if you care, don't pass things that
     * you don't want in your database.
     */
    public static void addStudent(Connection db, String name, int age) {
        try (PreparedStatement stmt = db.prepareStatement(
                "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            System.out.println("inserted null");
        } catch (SQLException err) {
            System.out.println("inserted " + err);
        }
    }
}