// All dependencies will be provided in the translated code so don't worry about them.
// To run this code, you would add `rusqlite` to your `Cargo.toml` file:
//
// [dependencies]
// rusqlite = "0.31.0"

use rusqlite::{Connection, Result};

// To make handling the data from the database easier and more type-safe,
// we define a struct that represents a row in our STUDENTS table.
#[derive(Debug)]
struct Student {
    id: i64, // SQLite's rowid is a 64-bit integer
    name: String,
    age: i32,
}

/// Adds a new student to the database and prints all entries.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// * A `Result` which is `Ok(())` on success or a `rusqlite::Error` on failure.
///   Rust's error handling is explicit, unlike JavaScript's callbacks or exceptions.
fn add_new_entry(name: &str, age: i32) -> Result<()> {
    // `Connection::open` creates a new connection or opens an existing database file.
    // The `?` operator is used for error handling. If `Connection::open` returns an
    // error, the function will immediately return that error.
    let conn = Connection::open("database.db")?;

    // In the original JS, `db.serialize` ensures sequential execution. In Rust with
    // `rusqlite`, operations on a single connection are sequential by default,
    // so no special wrapper is needed.

    // For a robust example, we ensure the table exists.
    // `execute` is used for statements that don't return rows.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT NOT NULL,
            AGE  INTEGER
        )",
        [], // empty list of parameters.
    )?;

    // `conn.prepare` creates a reusable prepared statement, just like in the JS version.
    // This is the best way to pass parameters to prevent SQL injection.
    // We use a `let mut` because we will execute it later.
    let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")?;
    
    // `stmt.execute` runs the prepared statement with the given parameters.
    // The `?` operator again handles any potential errors.
    stmt.execute((name, age))?;

    // In the original JS, `stmt.finalize()` and `db.close()` are called manually.
    // Rust uses a concept called RAII (Resource Acquisition Is Initialization).
    // When `stmt` and `conn` go out of scope at the end of this function, their
    // resources are automatically cleaned up (finalized and closed).

    // Now, we query the database to print all entries.
    let mut stmt = conn.prepare("SELECT rowid, NAME, AGE FROM STUDENTS")?;

    // `stmt.query_map` is the idiomatic Rust equivalent of `db.each`.
    // It executes the query and applies a closure to each row to map it into
    // our `Student` struct. This returns an iterator.
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            id: row.get(0)?,   // Get value by column index
            name: row.get(1)?, // `?` handles potential conversion errors
            age: row.get(2)?,
        })
    })?;

    println!("--- Current Students ---");
    // We iterate over the results. Each item is a `Result<Student, Error>`.
    for student_result in student_iter {
        // We unwrap the result to get the `Student` struct.
        // The `?` would propagate an error if one occurred during row mapping.
        let student = student_result?;
        // `println!` is Rust's macro for printing formatted text to the console.
        println!("{}: {} {}", student.id, student.name, student.age);
    }
    println!("------------------------");

    // If the function reaches this point without any `?` operator returning early,
    // it means everything was successful. We return `Ok(())`.
    // The `()` is the "unit type", indicating success with no return value.
    Ok(())
}

fn main() {
    // We call our function with the sample data.
    // Since `add_new_entry` returns a `Result`, we should handle the possibility of an error.
    if let Err(err) = add_new_entry("XXXXX", 0) {
        eprintln!("Error adding new entry: {}", err);
    }
    
    // Example of another call
    if let Err(err) = add_new_entry("YYYYY", 25) {
        eprintln!("Error adding new entry: {}", err);
    }
}