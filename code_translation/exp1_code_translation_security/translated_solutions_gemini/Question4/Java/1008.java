import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class manages database operations for a 'students' table.
 * It uses JDBC with a SQLite database.
 */
public class StudentDatabase {

    // The database connection URL. For this example, we use an in-file SQLite database.
    // This will create a file named "students.db" in the project's root directory.
    private static final String DB_URL = "jdbc:sqlite:students.db";

    /**
     * Java method to create the SQL table "students" with 'name' and 'age'.
     * This is equivalent to the JavaScript createTable() function.
     */
    public void createTable() {
        // The SQL statement for creating a new table.
        // "CREATE TABLE IF NOT EXISTS" prevents errors if the table already exists.
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT," // AUTOINCREMENT is good practice for primary keys
                   + " name TEXT NOT NULL,"
                   + " age INTEGER NOT NULL"
                   + ");";

        // try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            // Execute the SQL statement
            stmt.execute(sql);
            System.out.println("Table 'students' created successfully (or already exists).");

        } catch (SQLException e) {
            // The JavaScript equivalent of the error callback.
            // We print the error to the console instead of showing an alert.
            System.err.println("Error occurred while creating the table: " + e.getMessage());
        }
    }

    /**
     * Java method to insert a String name and an int age into the 'students' table.
     * This is equivalent to the JavaScript insertStudent() function.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public void insertStudent(String name, int age) {
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";

        // try-with-resources for automatic connection and statement closing.
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the values for the placeholders (?)
            pstmt.setString(1, name); // The first '?' is parameter index 1
            pstmt.setInt(2, age);     // The second '?' is parameter index 2
            
            // Execute the insert statement
            pstmt.executeUpdate();
            System.out.println("Successfully inserted student: " + name);

        } catch (SQLException e) {
            System.err.println("Error occurred while inserting student: " + e.getMessage());
        }
    }

    /**
     * Main method to demonstrate the functionality.
     */
    public static void main(String[] args) {
        // Create an instance of our database manager
        StudentDatabase dbManager = new StudentDatabase();

        // 1. Create the table
        dbManager.createTable();

        // 2. Insert some students
        dbManager.insertStudent("Alice", 21);
        dbManager.insertStudent("Bob", 24);
        dbManager.insertStudent("Charlie", 19);
    }
}