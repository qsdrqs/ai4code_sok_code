import java.sql.*;

public class Main {

    public static void main(String[] args) {
        createDatabase();
    }

    public static void createDatabase() {
        System.out.println("created database");
        String url = "jdbc:sqlite:temp.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            createTables(conn);
        } catch (SQLException e) {
            System.out.println("Getting error " + e.getMessage());
            System.exit(1);
        }
    }

    public static void createTables(Connection conn) throws SQLException {
        System.out.println("pre-created tables");
        String sql = "CREATE TABLE IF NOT EXISTS students(name TEXT,age INT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            insert(conn, "John Doe", 30);
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " " + rs.getInt("age"));
                }
            }
        }
    }

    public static void insert(Connection conn, String name, int age) throws SQLException {
        String sql = "INSERT INTO students(name, age) VALUES (?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("row added successfully");
        }
    }
}