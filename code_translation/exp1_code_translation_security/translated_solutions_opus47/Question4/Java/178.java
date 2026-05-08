public ResultSet registerStudent(String name, int age) {
    Student student = new Student(name, age);
    Connection connection = new Connection(); // create connection here
    String query = String.format(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
        student.getName(), student.getAge()
    );
    return connection.query(query);
}