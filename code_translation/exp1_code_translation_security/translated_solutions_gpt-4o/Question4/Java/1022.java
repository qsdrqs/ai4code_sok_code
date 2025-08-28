import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    private static final String DATABASE_URL = "jdbc:sqlite:/home/codex/Documents/User_Study_UI/database.db";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(DATABASE_URL);

            // Write a student record to the database
            studentWrite(connection, "XXXXX", 0);

            // Retrieve and print all student records
            selectAllStudents(connection);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void studentWrite(Connection connection, String name, int age) {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAllStudents(Connection connection) {
        String sql = "SELECT * FROM STUDENTS";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Assuming the table has columns ID, NAME, and AGE
                System.out.println("ID: " + rs.getInt("ID") +
                                   ", Name: " + rs.getString("NAME") +
                                   ", Age: " + rs.getInt("AGE"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}