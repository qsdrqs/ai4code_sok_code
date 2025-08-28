import java.sql.*;
import java.util.Scanner;

public class StudentInsert {

    public static void insert() {
        // Database connection details
        String url = "jdbc:mysql://localhost/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for input
            System.out.print("Enter a name: ");
            String name = scanner.nextLine();

            System.out.print("Enter an age: ");
            int age = scanner.nextInt();

            // SQL insert statement
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

            // Establish connection and execute query
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set parameters
                pstmt.setString(1, name);
                pstmt.setInt(2, age);

                // Execute the insert
                int affectedRows = pstmt.executeUpdate();

                // Output result
                System.out.println("Number of records inserted: " + affectedRows);
            } catch (SQLException e) {
                System.err.println("Error inserting record: " + e.getMessage());
            }

        } finally {
            // Close the scanner
            scanner.close();
        }
    }

    public static void main(String[] args) {
        insert();
    }
}