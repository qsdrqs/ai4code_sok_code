/*
 * A thin, direct translation of the original JavaScript helper into Java.
 * All required dependencies (java.sql.* and a valid `Connection`) are assumed
 * to be available/provided elsewhere in your codebase.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StudentRepository {

    /** Reference to an already-configured MySQL connection. */
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new student record (NAME, AGE) into the STUDENTS table.
     *
     * @param name the student's name
     * @param age  the student's age
     * @throws SQLException if the database rejects or fails to execute the SQL
     */
    public void addStudent(String name, int age) throws SQLException {
        final String sql =
            "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // try-with-resources closes the PreparedStatement automatically
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();  // no result set expected
        }
    }
}