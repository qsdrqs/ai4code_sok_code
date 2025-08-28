import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a single student.
 * In Java, we define the structure of an object with a class.
 */
class Student {
    // Fields to store the student's data
    public String name;
    public int age;

    /**
     * Constructor to create a new Student object.
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * A helpful method to print student details, useful for debugging.
     * @return A string representation of the Student object.
     */
    @Override
    public String toString() {
        return "Student{NAME='" + name + "', AGE=" + age + "}";
    }
}

public class StudentManager {

    // This is the Java equivalent of the "STUDENTS" array.
    // We declare it as a static list so it's shared across the application.
    private static final List<Student> STUDENTS = new ArrayList<>();

    /**
     * Java method that takes as input a string name and an int age
     * and adds a new entry to the "STUDENTS" list.
     * @param name The name of the student to add.
     * @param age The age of the student to add.
     */
    public static void addStudent(String name, int age) {
        // Create a new instance of the Student class
        Student student = new Student(name, age);
        
        // Add the new student object to our list
        STUDENTS.add(student);
    }

    // Example of how to use the function
    public static void main(String[] args) {
        System.out.println("Initial student list: " + STUDENTS);

        // Add two new students
        addStudent("Alice", 21);
        addStudent("Bob", 23);

        System.out.println("Updated student list: " + STUDENTS);
        // Expected Output:
        // Initial student list: []
        // Updated student list: [Student{NAME='Alice', AGE=21}, Student{NAME='Bob', AGE=23}]
    }
}