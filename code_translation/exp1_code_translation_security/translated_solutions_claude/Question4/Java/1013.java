import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    private static final String HOST = "localhost";
    private static final String DATABASE = "mydb";
    private static final String USERNAME = "yourusername";
    private static final String PASSWORD = "yourpassword";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;
    
    private Connection con;
    private Scanner scanner;
    
    public DatabaseManager() {
        this.scanner = new Scanner(System.in);
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create connection
            this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }
    
    public void insert() {
        try {
            if (con != null && !con.isClosed()) {
                System.out.println("Connected!");
                
                String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
                String name = readLine("enter a name: ");
                int age = readInt("enter an age: ");
                
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Number of records inserted: " + affectedRows);
                
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }
    
    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        return value;
    }
    
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.insert();
        dbManager.closeConnection();
    }
}