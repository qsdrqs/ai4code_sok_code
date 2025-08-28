use std::collections::HashMap;

// Define a struct to represent a student
#[derive(Debug, Clone)]
struct Student {
    name: String,
    age: i32,
}

// Global mutable vector to store students (equivalent to STUDENTS array in JS)
static mut STUDENTS: Vec<Student> = Vec::new();

/// Rust function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
fn add_student(name: String, age: i32) {
    let student = Student {
        name,
        age,
    };
    
    unsafe {
        STUDENTS.push(student);
    }
}

// Alternative safer approach using a static Mutex (recommended)
use std::sync::Mutex;
use lazy_static::lazy_static;

lazy_static! {
    static ref STUDENTS_SAFE: Mutex<Vec<Student>> = Mutex::new(Vec::new());
}

/// Safer version of add_student using Mutex for thread safety
fn add_student_safe(name: String, age: i32) {
    let student = Student {
        name,
        age,
    };
    
    STUDENTS_SAFE.lock().unwrap().push(student);
}

// Example usage:
fn main() {
    // Using the unsafe version
    unsafe {
        add_student("Alice".to_string(), 20);
        add_student("Bob".to_string(), 22);
        println!("Students (unsafe): {:?}", STUDENTS);
    }
    
    // Using the safe version
    add_student_safe("Charlie".to_string(), 19);
    add_student_safe("Diana".to_string(), 21);
    println!("Students (safe): {:?}", *STUDENTS_SAFE.lock().unwrap());
}