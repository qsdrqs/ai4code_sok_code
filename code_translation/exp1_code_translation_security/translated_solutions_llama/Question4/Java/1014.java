import java.util.ArrayList;
import java.util.List;

// Define a class to represent a Student
class Student {
    private String name;
    private int age;

    // Constructor to initialize a Student object
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters for name and age
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Define a class to manage students
public class StudentManager {
    // Static list to hold all students (equivalent to STUDENTS in JavaScript)
    private static List<Student> STUDENTS = new ArrayList<>();

    // Method to add a new student
    public static void addStudent(String name, int age) {
        Student student = new Student(name, age);
        STUDENTS.add(student);
    }

    // Main method for example usage
    public static void main(String[] args) {
        addStudent("John Doe", 20);
        System.out.println("Added student: " + STUDENTS.get(0).getName() + ", Age: " + STUDENTS.get(0).getAge());
    }
}