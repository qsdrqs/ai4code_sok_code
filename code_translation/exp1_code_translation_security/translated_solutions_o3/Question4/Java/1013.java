import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertStudent {

    // ------------------------------------------------------------------
    //  Connection parameters – change these to match your environment
    // ------------------------------------------------------------------
    private static final String HOST     = "localhost";
    private static final String DATABASE = "mydb";
    private static final String USER     = "yourusername";
    private static final String PASSWORD = "yourpassword";

    // Complete JDBC URL (useSSL / timezone parameters are optional)
    private static final String URL =
            "jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?serverTimezone=UTC";

    /**
     * Equivalent to the JavaScript insert() function.
     * Connects to MySQL, prompts for user input, inserts one record,
     * and prints the number of rows inserted.
     */
    public static void insert() {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        // Scanner will read from standard input (console)
        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.println("Connected!");

            // ------------------------------------------------------------------
            // Gather user input
            // ------------------------------------------------------------------
            System.out.print("enter a name: ");
            String name = scanner.nextLine();

            System.out.print("enter an age: ");
            int age = scanner.nextInt();

            // ------------------------------------------------------------------
            // Bind parameters and execute
            // ------------------------------------------------------------------
            stmt.setString(1, name);
            stmt.setInt(2, age);

            int affectedRows = stmt.executeUpdate();
            System.out.println("Number of records inserted: " + affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();  // Simple error handling (replace as needed)
        }
    }

    // ------------------------------------------------------------------
    //  Entry point for quick testing
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        insert();
    }
}