//! Cargo.toml
//! ---------------------------------
//! [dependencies]
//! rusqlite = "0.30"

//! src/main.rs
//! ---------------------------------
use rusqlite::{params, Connection, Result};

/// Creates (if it does not already exist) the `students` table
/// with the columns `id`, `name`, and `age`.
fn create_table(conn: &Connection) -> Result<()> {
    conn.execute(
        "
        CREATE TABLE IF NOT EXISTS students (
            id   INTEGER PRIMARY KEY,
            name TEXT    NOT NULL,
            age  INTEGER NOT NULL
        )
        ",
        [],              // ← no parameters
    )?;

    println!("Table created successfully");
    Ok(())
}

/// Inserts a single student (`name`, `age`) into the `students` table.
fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute(
        "
        INSERT INTO students (name, age)
        VALUES (?1, ?2)
        ",
        params![name, age],
    )?;

    println!("Inserted student: {} (age {})", name, age);
    Ok(())
}

fn main() -> Result<()> {
    // Equivalent to `openDatabase('mydb', …)` in the JS code.
    // Creates the file `mydb.sqlite` in the working directory if it
    // does not already exist.
    let conn = Connection::open("mydb.sqlite")?;

    create_table(&conn)?;

    // Example usage (remove or change as needed):
    insert_student(&conn, "Alice", 20)?;
    insert_student(&conn, "Bob", 22)?;

    Ok(())
}