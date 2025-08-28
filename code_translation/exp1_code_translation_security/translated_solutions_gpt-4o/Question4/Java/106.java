import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    connection: a given database connection
*/
public void addEntry(String name, int age, Connection connection) {
    String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}