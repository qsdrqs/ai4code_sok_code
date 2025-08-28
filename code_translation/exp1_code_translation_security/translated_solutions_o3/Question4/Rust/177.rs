// main.rs
// ──────────────────────────────────────────────────────────────
// This translation keeps the original spirit of the JavaScript
// code (string-building an INSERT statement and executing it).
// A real-world program should use prepared statements to avoid
// SQL-injection issues, but the goal here is a 1-for-1 port.

// ── Dependencies (Cargo.toml) ──
// [dependencies]
// rusqlite = "0.30"   // or any version supplied for you
// ──────────────────────────────────────────────────────────────

use rusqlite::{Connection, Result};

/// Mimics the JavaScript `addStudent` function.
///
/// `name`  – Student’s name  
/// `age`   – Student’s age
pub fn add_student(name: &str, age: i32) -> Result<()> {
    // Equivalent to: `var connection = new ActiveXObject("ADODB.Connection");`
    // (Here we open an SQLite database file named `students.db`.)
    let conn = Connection::open("students.db")?;

    // Equivalent to building the SQL string in JavaScript.
    let command = format!("INSERT INTO STUDENTS VALUES ('{}', '{}');", name, age);

    // Equivalent to: `rs.Open(command, connection);`
    conn.execute(&command, [])?;

    // Equivalent to `connection.close();`
    // (The connection is closed automatically when `conn` goes out of scope.)
    Ok(())
}

// Example usage
fn main() -> Result<()> {
    add_student("Alice", 20)?;
    add_student("Bob", 22)?;
    Ok(())
}