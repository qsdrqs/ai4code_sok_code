import java.sql.*;

public class Main {

    private static final String DB_URL = "/home/codex/Documents/User_Study_UI/database.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_URL)) {
            studentWrite(conn, "XXXXX", 0);

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS")) {

                while (rs.next()) {
                    int id = rs.getInt("ID"); // Assuming ID is the primary key
                    String name = rs.getString("NAME");
                    int age = rs.getInt("AGE");

                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void studentWrite(Connection conn, String name, int age) throws SQLException {
        String query = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }
}