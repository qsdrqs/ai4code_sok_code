import java.sql.*;

public class Main {

    // Function to add a new student to the database
    public static int addStudent(Connection conn, String name, int age) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        int result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    public static void main(String[] args) {
        // Database connection URL
        String url = "jdbc:sqlite:./database.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            // Create table if it doesn't exist
            String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTableSql);
            stmt.close();

            // Add a new student
            String name = "XXXXX";
            int age = 0;
            int rowsAffected = addStudent(conn, name, age);
            System.out.println("Rows affected: " + rowsAffected);

            // Display contents of database
            String selectSql = "SELECT * FROM STUDENTS";
            PreparedStatement pstmt = conn.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("NAME") + ", " + rs.getInt("AGE"));
            }
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}