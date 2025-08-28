// Define the Student struct to mirror the JavaScript object
struct Student {
    name: String,
    age: i32,
}

// Translate the registerStudent function
fn registerStudent(name: String, age: i32) -> impl std::fmt::Debug {
    // Create a Student instance
    let student = Student { name, age };

    // Create a new connection (assumed to be provided)
    let connection = Connection::new();

    // Construct the SQL query using the student's data
    let query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')",
        student.name, student.age
    );

    // Execute the query and return the result
    connection.query(query)
}