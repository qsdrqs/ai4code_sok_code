import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseInsert {

    public static void main(String[] args) {
        insert();
    }

    public static void insert() {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "yourusername";
        String password = "yourpassword";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

            String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a name: ");
            String name = scanner.nextLine();
            System.out.print("Enter an age: ");
            int age = scanner.nextInt();

            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Number of records inserted: " + affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}