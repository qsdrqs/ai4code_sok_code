import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {
    
    // db: Connection object, assumed to contain a STUDENTS (NAME, AGE) table
    // name: student name string
    // age: student age integer
    //
    // This function doesn't do any error checking on the types or sizes of `name`
    // and `age`, so if you care, don't pass things that you don't want in your database.
    public static void addStudent(Connection db, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            System.out.println("inserted null"); // equivalent to err being null on success
        } catch (SQLException err) {
            System.out.println("inserted " + err.getMessage());
        }
    }
}