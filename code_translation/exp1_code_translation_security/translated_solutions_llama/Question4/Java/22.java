import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    // Assuming STUDENTS is a static map
    private static Map<String, Integer> STUDENTS = new HashMap<>();

    /**
     * Adds a student to the STUDENTS map.
     * 
     * @param name The student's name.
     * @param age  The student's age.
     */
    public static void myFunction(String name, int age) {
        STUDENTS.put(name, age);
    }

    public static void main(String[] args) {
        // Example usage:
        myFunction("John Doe", 20);
        System.out.println(STUDENTS); // prints: {John Doe=20}
    }
}