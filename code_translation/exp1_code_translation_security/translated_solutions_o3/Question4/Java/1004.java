/*
Java   method addStudent

Given a String name and int age, add entry to table STUDENTS
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddStudent {

    /* Equivalent to JS add_student(name, age) */
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        return sql;
    }

    public static void main(String[] args) {

        /* ---------- MySQL connection details ---------- */
        String url      = "jdbc:mysql://localhost:3306/yourdatabase"; // supply DB name
        String user     = "yourusername";
        String password = "yourpassword";
        /* ------------------------------------------------ */

        Connection con  = null;
        Statement  stmt = null;

        try {
            /* Load MySQL JDBC driver */
            Class.forName("com.mysql.cj.jdbc.Driver");

            /* Open connection (equivalent to con.connect(cb) in JS) */
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

            /* Build and execute SQL (same as con.query(add_student(...)) in JS) */
            stmt = con.createStatement();
            String sql = addStudent("XXXXX", 0);      // age is int here
            int result = stmt.executeUpdate(sql);     // rows affected
            System.out.println("Result: " + result);

        } catch (SQLException | ClassNotFoundException e) {
            /* If (err) throw err;  */
            e.printStackTrace();
        } finally {
            /* Close resources */
            try {
                if (stmt != null) stmt.close();
                if (con  != null) con.close();
            } catch (SQLException ignored) { }
        }
    }
}