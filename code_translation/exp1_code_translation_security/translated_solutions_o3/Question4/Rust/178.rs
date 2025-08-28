//! Equivalent Rust implementation of the JavaScript `registerStudent`.

use database::{Connection, QueryResult};   // <- supplied in your dependencies

/// Simple DTO mirroring the JavaScript `student` object
struct Student {
    name: String,
    age:  u32,
}

/// Inserts a student into the `STUDENTS` table.
///
/// Returns whatever the driver’s `query` method returns (`QueryResult` here),
/// so the caller can decide how to handle success/failure.
pub fn register_student(name: &str, age: u32) -> QueryResult<()> {
    // Create the student object
    let student = Student {
        name: name.to_owned(),
        age,
    };

    // Create connection (details handled by the provided `Connection` type)
    let mut connection = Connection::new()?;     // ← “create connection here”

    // Compose SQL (straight translation of the original JS)
    let query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')",
        student.name, student.age
    );

    // Execute and propagate the result
    connection.query(&query)
}