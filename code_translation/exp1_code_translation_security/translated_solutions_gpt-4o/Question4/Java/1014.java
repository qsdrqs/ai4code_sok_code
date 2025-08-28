import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    // A list to store student entries
    private static ArrayList<Map<String, Object>> STUDENTS = new ArrayList<>();

    // Method to add a student
    public static void addStudent(String name, int age) {
        // Create a new student entry as a map
        Map<String, Object> student = new HashMap<>();
        student.put("NAME", name);
        student.put("AGE", age);

        // Add the student entry to the STUDENTS list
        STUDENTS.add(student);
    }

    // Optional: Method to print all students (for testing purposes)
    public static void printStudents() {
        for (Map<String, Object> student : STUDENTS) {
            System.out.println("NAME: " + student.get("NAME") + ", AGE: " + student.get("AGE"));
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        addStudent("Alice", 20);
        addStudent("Bob", 22);
        printStudents();
    }
}