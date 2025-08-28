// In a real project, you would add this to your Cargo.toml file:
// [dependencies]
// lazy_static = "1.4.0"

use lazy_static::lazy_static;
use std::sync::Mutex;

// 1. Define a structure for a student, similar to a JS object.
// We use `String` (an owned string) and `u32` (a 32-bit unsigned integer).
#[derive(Debug)] // This allows us to print the struct for debugging
struct Student {
    name: String,
    age: u32,
}

// 2. Define the global, mutable STUDENTS list.
// - `lazy_static!` is a macro that creates a globally shared variable that is
//   initialized on its first use.
// - `Mutex` is a "mutual exclusion" lock. It ensures that only one thread can
//   access the data at a time, preventing race conditions. This is Rust's
//   safe way to handle global mutable state.
// - `Vec<Student>` is a vector (a growable list) of Student structs, like a JS array.
lazy_static! {
    static ref STUDENTS: Mutex<Vec<Student>> = Mutex::new(vec![]);
}

/// Adds a new student to the global STUDENTS list.
///
/// # Arguments
/// * `name` - The student's name (as a string slice).
/// * `age` - The student's age (as a 32-bit unsigned integer).
fn my_function(name: &str, age: u32) {
    // To modify the list, we must first lock it.
    // .lock() returns a Result, so we .unwrap() it for this example.
    // This gives us a mutable reference to the data inside the Mutex.
    let mut students_list = STUDENTS.lock().unwrap();

    // Create a new Student instance and push it into the vector.
    // We convert the `&str` slice to an owned `String` because the struct
    // needs to own its data.
    students_list.push(Student {
        name: name.to_string(),
        age, // Field init shorthand, same as `age: age`
    });
} // The lock on `students_list` is automatically released here.

// Example of how to use the function
fn main() {
    println!("STUDENTS list before: {:?}", STUDENTS.lock().unwrap());

    my_function("Alice", 21);
    my_function("Bob", 22);

    println!("STUDENTS list after: {:?}", STUDENTS.lock().unwrap());
}