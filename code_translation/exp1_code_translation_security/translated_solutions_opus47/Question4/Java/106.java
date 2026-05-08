import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    /*
    Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

    Args:
        name: string for the "NAME" of the entry
        age: int for the "AGE" of the entry
        db: a given database connection
    */
    public static void addEntry(String name, int age, Connection db) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        Statement stmt = db.createStatement();
        stmt.execute(sql);
    }
}