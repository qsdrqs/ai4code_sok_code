import java.sql.*;

public class StudentDB {

    // Static initializer to load the SQLite JDBC driver
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }
    }

    // Method to add a new student entry and print all entries
    public void addNewEntry(String name, int age) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {

            // Insert new student into the STUDENTS table
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO STUDENTS VALUES (?, ?)")) {
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.executeUpdate();
            }

            // Query and print all students
            try (Statement selectStmt = conn.createStatement();
                 ResultSet rs = selectStmt.executeQuery("SELECT rowid AS id, NAME, AGE FROM STUDENTS")) {

                while (rs.next()) {
                    long id = rs.getLong("id");
                    String studentName = rs.getString("NAME");
                    int studentAge = rs.getInt("AGE");
                    System.out.println(id + ": " + studentName + " " + studentAge);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred.");
            e.printStackTrace();
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        StudentDB db = new StudentDB();
        db.addNewEntry("XXXXX", 0);
    }
}