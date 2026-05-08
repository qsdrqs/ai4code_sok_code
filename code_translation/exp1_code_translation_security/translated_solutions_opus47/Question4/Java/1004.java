import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    /*
    Java function add_student

    Given a String name and int age, add entry to table STUDENTS
    */
    public static String add_student(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        return sql;
    }

    public static void main(String[] args) {
        Connection con = null;

        // Equivalent of con.connect()
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/",
                "yourusername",
                "yourpassword"
            );
            System.out.println("Connected!");
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }

        // Equivalent of con.query()
        try {
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(add_student("XXXXX", 0));
            System.out.println("Result: " + result);
            stmt.close();
            con.close();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
}