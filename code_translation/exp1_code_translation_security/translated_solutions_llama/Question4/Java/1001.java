import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    // Assuming db connection is established and provided as a parameter
    public static void addSQLentry(Connection dbConnection, String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

            // Get the generated key (id) from the database
            try (java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("User added with id: " + generatedKeys.getLong(1));
                } else {
                    System.out.println("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Load the SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            return;
        }

        // Establish a connection to the database
        try (Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:example.db")) {
            addSQLentry(dbConnection, "John Doe", 25);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}