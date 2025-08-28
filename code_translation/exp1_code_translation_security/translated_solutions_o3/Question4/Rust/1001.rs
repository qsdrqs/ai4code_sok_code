use rusqlite::{params, Connection, Result};

/// Inserts a new student into the `students` table.
///
/// `conn` – an open `rusqlite::Connection`.
/// `name` – the student’s name.
/// `age`  – the student’s age.
///
/// On success the function prints the auto-generated row-id and returns it.
/// If something goes wrong the error is printed and bubbled up to the caller.
pub fn add_sql_entry(conn: &Connection, name: &str, age: i32) -> Result<i64> {
    // Equivalent of: `INSERT INTO students (name, age) VALUES (?, ?);`
    let sql = "INSERT INTO students (name, age) VALUES (?1, ?2)";

    // Execute the statement with positional parameters.
    conn.execute(sql, params![name, age])?;

    // rusqlite exposes the last inserted row-id through this method.
    let id = conn.last_insert_rowid();
    println!("User added with id: {}", id);

    Ok(id)
}