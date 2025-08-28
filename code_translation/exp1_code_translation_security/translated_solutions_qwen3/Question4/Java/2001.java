import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    /**
     * Adds a student to the students table in the database.
     *
     * @param connection the active database connection
     * @param name       the name of the student
     * @param age        the age of the student
     * @throws SQLException if a database access error occurs
     */
    public static void addStudent(Connection connection, String name, int age) throws SQLException {
        String query = "INSERT INTO students (name, age) VALUES (?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
            System.out.println("Added " + name + " to students table");
        }
    }
}