public class StudentDatabase {

    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        return sql;
    }

    public static void main(String[] args) {
        // Example usage
        String sqlQuery = addStudent("XXXXX", 0);
        System.out.println(sqlQuery);
    }
}