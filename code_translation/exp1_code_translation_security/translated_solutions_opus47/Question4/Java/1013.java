import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    public static void insert() {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";
        
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected!");
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("enter a name: ");
            String name = scanner.nextLine();
            
            System.out.print("enter an age: ");
            int age = scanner.nextInt();
            
            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Number of records inserted: " + affectedRows);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        insert();
    }
}