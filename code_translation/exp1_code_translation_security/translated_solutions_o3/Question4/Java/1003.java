// StudentDAO.java
//
// Dependencies (all can be supplied via your build system):
//   – org.xerial:sqlite-jdbc  <version>
//   – Java SE (java.sql.*)
//
// Usage example
//  try (Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db")) {
//      StudentDAO.addStudent(conn, "Alice", 20);
//  }
//
// No attempt is made to validate `name` or `age` – the caller is responsible
// for passing sane values.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StudentDAO {

    private StudentDAO() {
        // Utility class – no instances.
    }

    /**
     * Inserts a single student row into the STUDENTS table.
     *
     * @param db   an open JDBC connection (e.g. to SQLite)
     * @param name the student's name
     * @param age  the student's age
     */
    public static void addStudent(Connection db, String name, int age) {
        final String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
            System.out.println("inserted null");   // mirrors JS: err == null
        } catch (SQLException e) {
            // Mirrors `console.log("inserted", err);` from JS version
            System.out.println("inserted " + e);
        }
    }
}