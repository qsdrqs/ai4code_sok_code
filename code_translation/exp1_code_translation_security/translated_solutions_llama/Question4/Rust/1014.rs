// Import necessary dependencies
use std::collections::Vec;

// Define a struct to represent a Student
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

// Define a vector to store all students
static mut STUDENTS: Vec<Student> = Vec::new();

// Function to add a new student to STUDENTS
fn add_student(name: &str, age: i32) {
    // Create a new Student instance
    let student = Student {
        name: name.to_string(),
        age,
    };

    // Add the new student to STUDENTS
    unsafe {
        STUDENTS.push(student);
    }
}

fn main() {
    // Example usage
    add_student("John Doe", 20);
    add_student("Jane Doe", 22);

    // Print all students (unsafe block required due to static mut STUDENTS)
    unsafe {
        println!("{:?}", STUDENTS);
    }
}