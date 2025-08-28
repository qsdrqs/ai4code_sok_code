// src/main.rs

// Import necessary items from the rusqlite crate
use rusqlite::{params, Connection, Result};

/// Rust function to create the "students" table.
/// It takes a mutable reference to a database connection.
/// Returns a Result, which is Ok(()) on success or an Err on failure.
fn create_table(conn: &Connection) -> Result<()> {
    // The `execute` method runs a SQL command.
    // The `params!` macro is used for SQL parameters, empty in this case.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS students (
            id    INTEGER PRIMARY KEY,
            name  TEXT NOT NULL,
            age   INTEGER
        )",
        [], // No parameters needed for this query
    )?;

    // In Rust, we handle success by returning Ok.
    // The `?` operator above handles the error case by returning the error early.
    println!("Table created successfully or already exists.");
    Ok(())
}

/// Rust function to insert a student's name (string) and age (integer) into the 'students' table.
/// It also takes a mutable reference to the database connection.
fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // A transaction is started. If the code inside the closure returns an error,
    // the transaction is automatically rolled back.
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;

    println!("Inserted student: {} with age: {}", name, age);
    Ok(())
}

// A simple struct to hold student data when we query it back
#[derive(Debug)]
struct Student {
    id: i32,
    name: String,
    age: i32,
}

fn main() -> Result<()> {
    // 1. Open a connection to a new or existing SQLite database file.
    // The `?` operator will propagate any errors that occur.
    let conn = Connection::open("students.db")?;
    println!("Database opened successfully.");

    // 2. Create the table.
    if let Err(e) = create_table(&conn) {
        eprintln!("Error creating table: {}", e);
        return Err(e); // Exit if table creation fails
    }

    // 3. Insert some students.
    // In the original JS, the DB was opened in each insert.
    // The better practice, shown here, is to open it once.
    insert_student(&conn, "Alice", 22)?;
    insert_student(&conn, "Bob", 25)?;
    insert_student(&conn, "Carol", 29)?;

    // 4. (Bonus) Let's query the data to prove it was inserted correctly.
    println!("\n--- Querying all students ---");
    let mut stmt = conn.prepare("SELECT id, name, age FROM students")?;
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            id: row.get(0)?,
            name: row.get(1)?,
            age: row.get(2)?,
        })
    })?;

    for student in student_iter {
        println!("Found student: {:?}", student?);
    }

    Ok(())
}