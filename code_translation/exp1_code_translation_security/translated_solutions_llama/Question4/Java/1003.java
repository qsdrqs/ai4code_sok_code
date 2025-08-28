import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    /**
     * Adds a student to the STUDENTS table in the database.
     * 
     * @param dbUrl  the URL of the SQLite database
     * @param name   the name of the student
     * @param age    the age of the student
     */
    public void addStudent(String dbUrl, String name, int age) {
        String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

            System.out.println("Inserted successfully");

        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent("students.db", "John Doe", 20);
    }
}