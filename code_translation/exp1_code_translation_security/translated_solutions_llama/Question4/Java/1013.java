import java.sql.*;
import java.util.Scanner;

public class Main {

    // Database connection properties
    private static final String HOST = "localhost";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";
    private static final String DATABASE = "mydb";

    public static void main(String[] args) {
        insert();
    }

    /**
     * Inserts a new student into the database.
     */
    public static void insert() {
        // Create a connection to the database
        String url = "jdbc:mysql://" + HOST + ":3306/" + DATABASE;
        try (Connection con = DriverManager.getConnection(url, USER, PASSWORD)) {
            System.out.println("Connected!");

            // Read user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a name: ");
            String name = scanner.nextLine();
            System.out.print("Enter an age: ");
            int age = scanner.nextInt();
            scanner.close();

            // Prepare and execute the INSERT query
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setInt(2, age);
                int result = stmt.executeUpdate();
                System.out.println("Number of records inserted: " + result);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}