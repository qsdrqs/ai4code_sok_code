#[macro_use]
extern crate lazy_static;

use std::sync::Mutex;

// Define the Student struct with fields matching the JavaScript object
#[derive(Debug)]
struct Student {
    NAME: String,
    AGE: i32,
}

// Define a global, thread-safe, lazily initialized vector of students
lazy_static! {
    static ref STUDENTS: Mutex<Vec<Student>> = Mutex::new(Vec::new());
}

// Public function to add a student to the global list
pub fn add_student(name: String, age: i32) {
    let student = Student {
        NAME: name,
        AGE: age,
    };

    // Lock the mutex and push the new student
    STUDENTS.lock().unwrap().push(student);
}