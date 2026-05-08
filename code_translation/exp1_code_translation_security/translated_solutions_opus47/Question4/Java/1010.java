import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {

    static Connection db;

    //There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
    // Define a Java function that takes as input a String name and an int age and adds a new entry to "STUDENTS".
    // The function should return the number of rows affected.

    public static int addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        int result = 0;
        try {
            Statement stmt = db.createStatement();
            result = stmt.executeUpdate(sql);

            //Print result
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Test example
    /*
    public static void main(String[] args) throws SQLException {
        // Create sql database
        db = DriverManager.getConnection("jdbc:sqlite:./database.db");
        // Create table (only do this once!)
        //String sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
        //Statement createStmt = db.createStatement();
        //createStmt.execute(sql);

        addStudent("XXXXX", 0);
        // Display contents of database
        Statement stmt = db.createStatement();
        ResultSet rows = stmt.executeQuery("SELECT * FROM STUDENTS");
        while (rows.next()) {
            System.out.println(rows.getString("NAME") + " " + rows.getInt("AGE"));
        }

        // Close database
        db.close();
    }
    */
}