import java.sql.SQLException;

/**
 * This file contains all the necessary classes for the example.
 * In a real-world application, these would be in separate files.
 */

// =======================================================================
// 1. DEPENDENCIES (as required by the prompt)
// These are mock classes to make the example runnable.
// =======================================================================

/**
 * A mock data-carrying class for a PreparedStatement.
 * In a real scenario, this would be an interface from the java.sql package.
 * It implements AutoCloseable to be used in a try-with-resources block.
 */
class PreparedStatement implements AutoCloseable {
    private final String sql;

    public PreparedStatement(String sql) {
        this.sql = sql;
        System.out.println("Prepared statement with SQL: " + this.sql);
    }

    public void setString(int parameterIndex, String value) {
        System.out.printf("Set parameter %d to (String) '%s'\n", parameterIndex, value);
    }

    public void setInt(int parameterIndex, int value) {
        System.out.printf("Set parameter %d to (int) %d\n", parameterIndex, value);
    }

    public int executeUpdate() throws SQLException {
        System.out.println("Executing update...");
        // Simulate that 1 row was successfully inserted.
        return 1;
    }

    @Override
    public void close() {
        System.out.println("PreparedStatement closed.");
    }
}

/**
 * A mock database connection class.
 * It implements AutoCloseable to ensure the connection is always closed.
 */
class Connection implements AutoCloseable {
    public Connection() {
        System.out.println("Database connection opened.");
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new PreparedStatement(sql);
    }

    @Override
    public void close() {
        System.out.println("Database connection closed.");
    }
}

/**
 * A POJO (Plain Old Java Object) to represent the Student.
 * This is the equivalent of the JavaScript object.
 */
class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


// =======================================================================
// 2. TRANSLATED LOGIC
// =======================================================================

public class StudentRegistry {

    /**
     * Registers a new student in the database.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     * @return The number of rows affected in the database (typically 1 for a successful insert).
     * @throws SQLException if a database access error occurs.
     */
    public int registerStudent(String name, int age) throws SQLException {
        // In Java, we use classes (POJOs) to structure data.
        Student student = new Student(name, age);

        // The SQL query uses '?' as placeholders for parameters.
        // This is crucial for preventing SQL injection attacks.
        String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // 'try-with-resources' automatically closes the connection and statement,
        // preventing resource leaks, even if errors occur.
        try (Connection connection = new Connection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Bind values to the placeholders safely.
            // The driver handles escaping special characters.
            statement.setString(1, student.getName()); // Parameter index starts at 1
            statement.setInt(2, student.getAge());

            // Execute the query and return the number of affected rows.
            return statement.executeUpdate();
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        StudentRegistry registry = new StudentRegistry();
        try {
            int rowsAffected = registry.registerStudent("Alice", 22);
            System.out.println("Success! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Failed to register student.");
            e.printStackTrace();
        }
    }
}