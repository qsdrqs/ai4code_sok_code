/**
 * This is a dependency class provided to make the example complete and runnable.
 * It represents the 'STUDENTS' object from the JavaScript code.
 */
class StudentCollection {
    /**
     * A method to add a student's details. In a real application, this would
     * likely add the student to a List, Map, or a database.
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void add(String name, int age) {
        System.out.println("Adding student: " + name + ", Age: " + age);
        // Real implementation would store the data here.
    }
}


public class MyClass {

    // In Java, a shared object like 'STUDENTS' is often represented as a static field.
    // 'final' means the STUDENTS variable cannot be reassigned to a new object.
    private static final StudentCollection STUDENTS = new StudentCollection();

    /**
     * Adds a new student to the STUDENTS collection.
     *
     * @param name The name of the student, as a String.
     * @param age The age of the student, as an int.
     */
    public void myFunction(String name, int age) {
        STUDENTS.add(name, age);
    }
}