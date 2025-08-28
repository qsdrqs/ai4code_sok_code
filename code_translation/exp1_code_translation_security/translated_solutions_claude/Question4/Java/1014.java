import java.util.ArrayList;

public class StudentManager {
    // Student class to represent the student object
    public static class Student {
        private String NAME;
        private int AGE;
        
        public Student(String name, int age) {
            this.NAME = name;
            this.AGE = age;
        }
        
        // Getters
        public String getNAME() { return NAME; }
        public int getAGE() { return AGE; }
        
        // Setters
        public void setNAME(String name) { this.NAME = name; }
        public void setAGE(int age) { this.AGE = age; }
    }
    
    private static ArrayList<Student> STUDENTS = new ArrayList<>();
    
    /**
     * Java method that takes as input a String name and an int age and adds a new entry to "STUDENTS"
     */
    public static void addStudent(String name, int age) {
        Student student = new Student(name, age);
        STUDENTS.add(student);
    }
}