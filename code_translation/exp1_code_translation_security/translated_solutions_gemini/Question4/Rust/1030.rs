use rusqlite::{params, Connection, Result};
use std::fs;

// A struct to hold the data retrieved from the database.
// The `Debug` trait is added to allow for easy printing with `println!`.
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

fn main() {
    // We wrap the main logic in a function that returns a Result
    // for cleaner error handling with the `?` operator.
    if let Err(err) = run() {
        // If any operation fails, print the error to stderr.
        eprintln!("ERROR: {}", err);
        std::process::exit(1);
    }
}

/// This function orchestrates all the database operations.
/// It returns a `Result<()>` which is `Ok(())` on success
/// or an `Err` if any step fails.
fn run() -> Result<()> {
    // For a clean run every time, we can delete the old database file.
    // The `_` ignores the result of the file removal (e.g., if it didn't exist).
    let _ = fs::remove_file("temp.db");

    // In JS: `new sqlite3.Database('temp.db', callback)`
    // In Rust, `Connection::open` creates and connects to the database.
    // The `?` operator will automatically return an `Err` if the connection fails.
    println!("Creating/connecting to database 'temp.db'...");
    let conn = Connection::open("temp.db")?;

    // In JS: `createTables2(newdb)` was called in the connection callback.
    // In Rust, we just call the function sequentially.
    create_tables(&conn)?;

    // In JS: `insert(...)` was called in the create table callback.
    // In Rust, we continue the sequence.
    insert(&conn, "John Doe", 30)?;

    // This part corresponds to the commented-out `db.all(...)` in the JS.
    // It demonstrates how to query and print the results.
    println!("\nQuerying all students from the database:");
    query_and_print_students(&conn)?;

    Ok(()) // Return `Ok` to indicate success.
}

/// Corresponds to the `createTables2` function in JavaScript.
fn create_tables(conn: &Connection) -> Result<()> {
    println!("Creating table 'students'...");
    // In JS: `db.run('CREATE TABLE ...')`
    // In Rust, `conn.execute` runs a command that doesn't return rows.
    conn.execute(
        "CREATE TABLE students (
            name TEXT NOT NULL,
            age  INTEGER
        )",
        [], // An empty slice for no parameters.
    )?;
    println!("Table created successfully.");
    Ok(())
}

/// Corresponds to the `insert` function in JavaScript.
fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
    println!("Inserting ('{}', {}) into students...", name, age);
    // In JS: `db.run(sql, [name, age], callback)`
    // In Rust, we use `?` placeholders and the `params!` macro for type-safe parameters.
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;
    println!("Row added successfully.");
    Ok(())
}

/// A new function to demonstrate querying, based on the commented-out JS.
fn query_and_print_students(conn: &Connection) -> Result<()> {
    // `conn.prepare` creates a reusable statement.
    let mut stmt = conn.prepare("SELECT name, age FROM students")?;

    // `query_map` executes the query and maps each row to a Rust struct.
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?, // Get value from column 0
            age: row.get(1)?,  // Get value from column 1
        })
    })?;

    // Loop through the iterator of results.
    for student in student_iter {
        // Each item is a `Result<Student, Error>`, so we print it.
        println!("  - Found: {:?}", student?);
    }

    Ok(())
}