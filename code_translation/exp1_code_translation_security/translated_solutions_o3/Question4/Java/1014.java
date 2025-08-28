import java.util.ArrayList;
import java.util.List;

/*
 * Java utility that mirrors the JavaScript logic:
 *  – Holds a global collection STUDENTS.
 *  – Provides addStudent(String name, int age) to append a new entry.
 */
public final class StudentRegistry {

    // Global collection equivalent to the JS “STUDENTS” array
    public static final List<Student> STUDENTS = new ArrayList<>();

    // Equivalent to the JavaScript addStudent(name, age) function
    public static void addStudent(String name, int age) {
        Student student = new Student(name, age);
        STUDENTS.add(student);
    }

    // Simple POJO that represents each student entry
    public static class Student {
        public final String NAME;
        public final int AGE;

        public Student(String name, int age) {
            this.NAME = name;
            this.AGE  = age;
        }
    }
}