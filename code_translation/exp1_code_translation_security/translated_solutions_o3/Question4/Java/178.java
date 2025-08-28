// StudentRepository.java  (or any class where you want the method to live)
public class StudentRepository {

    /**
     * Inserts a new student into the STUDENTS table.
     *
     * @param name the student's name
     * @param age  the student's age
     * @return whatever type Connection#query returns (e.g. ResultSet, int, boolean, etc.)
     */
    public Object registerStudent(String name, int age) {
        // Create the DTO just like in the JS version
        Student student = new Student(name, age);

        // Obtain a connection (class supplied elsewhere)
        Connection connection = new Connection();

        // Build the SQL statement
        String query =
            "INSERT INTO STUDENTS (NAME, AGE) VALUES ('"
            + student.getName() + "', '" + student.getAge() + "')";

        // Execute and return the result
        return connection.query(query);
    }
}