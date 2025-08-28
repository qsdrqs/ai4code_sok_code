public class StudentService {
    
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        return sql;
    }
    
    public static void main(String[] args) {
        // addStudent("XXXXX", 0);
    }
}