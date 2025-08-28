import java.sql.*;

public class Main {

    public static void addNewEntry(String name, int age) {
        // SQLite database connection URL
        String url = "jdbc:sqlite:database.db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STUDENTS VALUES (?,?)")) {

            // Insert a new entry
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

            // Retrieve and print all entries
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT rowid AS id, NAME, AGE FROM STUDENTS")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String studentName = rs.getString("NAME");
                    int studentAge = rs.getInt("AGE");
                    System.out.println(id + ": " + studentName + " " + studentAge);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        addNewEntry("XXXXX", 0);
    }
}