import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDb {

    private static final String DB_URL = "jdbc:sqlite:studentdb";
    private static Connection connection;

    public static void main(String[] args) {
        try {
            openConnection();
            createTableIfNotExists();

            insert("Foo", 42);
            insert("Bar", 55);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Opens a connection to the SQLite database
    private static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }

    // Creates the STUDENTS table if it doesn't already exist
    private static void createTableIfNotExists() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
        }
    }

    // Inserts a new student record
    private static void insert(String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }

        /* Uncomment if you want to print the table after each insert
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS")) {

            while (rs.next()) {
                System.out.println("NAME = " + rs.getString("NAME") +
                                   ", AGE = " + rs.getInt("AGE"));
            }
        }
        */
    }

    // Closes the database connection
    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
    }
}