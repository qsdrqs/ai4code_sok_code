import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @requires variable name {connection} corresponding to a mysql db connection
 */
public void addStudent(String name, int age) {
    String q = "INSERT INTO STUDENTS(NAME, AGE) " +
               "VALUES(?,?)";
    try (PreparedStatement stmt = connection.prepareStatement(q)) {
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.executeUpdate();
    } catch (SQLException e) {
        // Empty handler, matching the empty JS callback
    }
}