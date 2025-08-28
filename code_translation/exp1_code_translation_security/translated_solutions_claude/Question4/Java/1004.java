import java.sql.*;

public class StudentDatabase {
    
    private static Connection con;
    
    /**
     * Java method add_student
     * 
     * Given a String name and int age, add entry to table STUDENTS
     */
    public static String add_student(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        return sql;
    }
    
    public static void main(String[] args) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database_name",
                "yourusername", 
                "yourpassword"
            );
            
            System.out.println("Connected!");
            
            // Execute query
            Statement stmt = con.createStatement();
            String query = add_student("XXXXX", 0);
            int result = stmt.executeUpdate(query);
            
            System.out.println("Result: " + result);
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            // Close connection
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}