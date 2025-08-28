import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class StudentsDB {

    private Connection connection;

    public StudentsDB(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new entry to the SQL table "STUDENTS"
     */
    public void add(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("1 record inserted");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting record", e);
        }
    }
}