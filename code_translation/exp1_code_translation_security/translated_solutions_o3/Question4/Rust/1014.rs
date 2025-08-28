// --- Cargo.toml ---
// [dependencies]
// once_cell = "1.19"

// --- src/lib.rs (or main.rs) ---
use once_cell::sync::Lazy;
use std::sync::Mutex;

/// Simple record representing a student.
#[derive(Debug, Clone)]
pub struct Student {
    pub name: String,
    pub age:  u32,
}

/// Global list that plays the role of the JavaScript `STUDENTS` array.
/// `Lazy` ensures it is created on first use; `Mutex` gives interior mutability.
pub static STUDENTS: Lazy<Mutex<Vec<Student>>> = Lazy::new(|| Mutex::new(Vec::new()));

/// Rust equivalent of `function addStudent(name, age) { ... }`
pub fn add_student<N: Into<String>>(name: N, age: u32) {
    let student = Student {
        name: name.into(),
        age,
    };

    // Push the new entry onto the global list.
    STUDENTS
        .lock()
        .expect("mutex poisoned")  // unwrap the lock
        .push(student);
}

// ------------------------------------------------------------------
// Optional demo / unit test
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn adding_students_works() {
        // Clear any earlier state (only for testing convenience).
        STUDENTS.lock().unwrap().clear();

        add_student("Alice", 20);
        add_student("Bob", 22);

        let list = STUDENTS.lock().unwrap();
        assert_eq!(list.len(), 2);
        assert_eq!(list[0].name, "Alice");
        assert_eq!(list[1].age, 22);
    }
}