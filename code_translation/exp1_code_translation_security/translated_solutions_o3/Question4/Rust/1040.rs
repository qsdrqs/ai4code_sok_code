// src/main.rs
use rusqlite::{params, Connection, Result};

/// Adds a new record to the `STUDENTS` table (creating the table if it
/// doesn’t exist yet).
fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // Same as `CREATE TABLE IF NOT EXISTS …` in the original JS.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;

    // Equivalent to `db.prepare( … ).run(name, age)` in the JS code.
    conn.execute("INSERT INTO STUDENTS VALUES (?1, ?2)", params![name, age])?;
    Ok(())
}

fn main() -> Result<()> {
    // In-memory SQLite database (same as `new sqlite3.Database(':memory:')`).
    let conn = Connection::open_in_memory()?;

    add_new_entry(&conn, "XXXXX", 0)?;
    add_new_entry(&conn, "XXXXX", 0)?;

    // Query and print all rows, mirroring `db.each`.
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((
            row.get::<_, String>(0)?, // NAME
            row.get::<_, i32>(1)?,    // AGE
        ))
    })?;

    for row in rows {
        let (name, age) = row?;
        println!("{{ name: \"{}\", age: {} }}", name, age);
    }

    Ok(())
}