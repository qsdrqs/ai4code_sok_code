/*
Java function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
*/
public static void addStudent(String name, int age) {
    Map<String, Object> student = new HashMap<>();
    student.put("NAME", name);
    student.put("AGE", age);
    STUDENTS.add(student);
}