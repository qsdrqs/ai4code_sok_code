public class StudentSqlBuilder {

    /**
     * Builds an INSERT statement for the STUDENTS table.
     *
     * @param name the student’s name
     * @param age  the student’s age
     * @return the SQL string
     */
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        return sql;
    }

    // Example usage:
    // public static void main(String[] args) {
    //     System.out.println(addStudent("XXXXX", 0));
    // }
}