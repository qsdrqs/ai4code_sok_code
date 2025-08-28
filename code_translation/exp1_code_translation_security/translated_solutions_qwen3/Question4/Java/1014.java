import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    // Static list to mimic the global STUDENTS array in JavaScript
    public static List<Student> STUDENTS = new ArrayList<>();

    /**
     * Adds a new student to the STUDENTS list.
     * 
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void addStudent(String name, int age) {
        Student student = new Student();
        student.NAME = name;
        student.AGE = age;
        STUDENTS.add(student);
    }

    // Example usage (optional)
    public static void main(String[] args) {
        addStudent("Alice", 20);
        addStudent("Bob", 22);

        for (Student s : STUDENTS) {
            System.out.println("Name: " + s.NAME + ", Age: " + s.AGE);
        }
    }
}

// Student class with public fields to match JavaScript object literal
class Student {
    public String NAME;
    public int AGE;
}