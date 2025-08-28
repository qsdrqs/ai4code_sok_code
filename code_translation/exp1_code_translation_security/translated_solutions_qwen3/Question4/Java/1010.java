import java.sql.*;

public class SQLiteTranslator {

    public static void main(String[] args) {
        Connection db = null;
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            db = DriverManager.getConnection("jdbc:sqlite:database.db");

            // Create table (only do this once!)
            // Statement stmt = db.createStatement();
            // stmt.execute("CREATE TABLE STUDENTS (NAME TEXT, AGE INT)");
            // stmt.close();

            // Add a new student
            int result = addStudent("XXXXX", 0, db);
            System.out.println("Rows affected: " + result);

            // Display contents of the database
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
            }

            // Close resources
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (db != null && !db.isClosed()) {
                    db.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a new student to the STUDENTS table.
     *
     * @param name the name of the student
     * @param age  the age of the student
     * @param db   the database connection
     * @return the number of rows affected
     * @throws SQLException if a database access error occurs
     */
    public static int addStudent(String name, int age, Connection db) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        Statement stmt = db.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        stmt.close(); // Close the statement to prevent resource leak
        return rowsAffected;
    }
}