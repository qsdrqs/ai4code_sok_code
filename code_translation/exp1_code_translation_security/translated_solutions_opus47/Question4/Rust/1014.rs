use once_cell::sync::Lazy;
use std::sync::Mutex;

#[allow(non_snake_case)]
struct Student {
    NAME: String,
    AGE: i32,
}

// Global mutable collection, equivalent to the JS global `STUDENTS` array.
// We wrap it in a Mutex because global mutable state must be synchronized in Rust.
static STUDENTS: Lazy<Mutex<Vec<Student>>> = Lazy::new(|| Mutex::new(Vec::new()));

/// Rust function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
fn add_student(name: String, age: i32) {
    let student = Student {
        NAME: name,
        AGE: age,
    };
    STUDENTS.lock().unwrap().push(student);
}