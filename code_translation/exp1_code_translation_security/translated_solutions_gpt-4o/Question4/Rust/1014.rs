// Assuming STUDENTS is a mutable global vector of students
use std::sync::Mutex;

// Define a struct to represent a student
#[derive(Debug)]
struct Student {
    name: String,
    age: u32,
}

// Global mutable STUDENTS vector wrapped in a Mutex for thread safety
lazy_static::lazy_static! {
    static ref STUDENTS: Mutex<Vec<Student>> = Mutex::new(Vec::new());
}

// Function to add a student to the STUDENTS vector
fn add_student(name: &str, age: u32) {
    let student = Student {
        name: name.to_string(),
        age,
    };

    // Lock the STUDENTS vector and push the new student
    let mut students = STUDENTS.lock().unwrap();
    students.push(student);
}