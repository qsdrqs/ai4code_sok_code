public class StudentRegistration {

    public static Object registerStudent(String name, int age) {
        Connection connection = new Connection();
        String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', '" + age + "')";
        return connection.query(query);
    }
}