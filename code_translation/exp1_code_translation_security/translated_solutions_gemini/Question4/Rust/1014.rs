// In a real Rust project, you would add `lazy_static` to your Cargo.toml file:
// [dependencies]
// lazy_static = "1.4.0"

use lazy_static::lazy_static;
use std::sync::Mutex;

// First, we define the structure for a student, which is equivalent to the
// JavaScript object. In Rust, we use `struct`.
// `#[derive(Debug)]` is added to allow the struct to be printed for debugging.
#[derive(Debug)]
struct Student {
    name: String,
    age: u32,
}

// In Rust, global mutable state is handled carefully to prevent data races.
// We use `lazy_static!` to initialize a complex global variable at runtime.
// We wrap our `Vec<Student>` (a growable list, like a JS array) in a `Mutex`.
// A Mutex ensures that only one part of the code can access the data at a time,
// making it safe to use across different threads.
lazy_static! {
    static ref STUDENTS: Mutex<Vec<Student>> = Mutex::new(Vec::new());
}

/*
Rust function that takes as input a String `name` and a u32 `age`
and adds a new entry to the global "STUDENTS" vector.
*/
fn add_student(name: String, age: u32) {
    // Create a new instance of the Student struct.
    // We can use the field init shorthand because the variable names `name` and `age`
    // match the struct's field names.
    let student = Student { name, age };

    // To modify the global STUDENTS vector, we must first lock the Mutex.
    // .lock() returns a Result, which we `.unwrap()` for simplicity in this example.
    // This gives us exclusive access to the vector.
    STUDENTS.lock().unwrap().push(student);
}

// A main function is required to run the code and demonstrate its usage.
fn main() {
    println!("Adding Alice (age 22)...");
    add_student("Alice".to_string(), 22);

    println!("Adding Bob (age 25)...");
    add_student("Bob".to_string(), 25);

    // To view the students, we lock the mutex again and print the contents.
    // The `{:?}` format specifier uses the `Debug` trait we derived for the struct.
    println!("\nCurrent students: {:?}", STUDENTS.lock().unwrap());
}