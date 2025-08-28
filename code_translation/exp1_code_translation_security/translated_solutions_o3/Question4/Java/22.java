public class StudentService {

    // Assumes that the STUDENTS reference (with an add(String, int) method) is
    // available elsewhere in your project.
    public static void myFunction(String name, int age) {
        STUDENTS.add(name, age);
    }
}