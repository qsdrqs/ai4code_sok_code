// main.rs

use rusqlite::{params, Connection, Result};

/// Insert one entry into STUDENTS and print every row afterwards
fn add_new_entry(name: &str, age: i32) -> Result<()> {
    // Open (or create) the SQLite database
    let conn = Connection::open("database.db")?;

    // Insert the new record
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;

    // Query all rows and print them
    let mut stmt = conn.prepare("SELECT rowid AS id, NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((
            row.get::<_, i64>(0)?,   // id
            row.get::<_, String>(1)?, // NAME
            row.get::<_, i32>(2)?,    // AGE
        ))
    })?;

    for row in rows {
        let (id, name, age) = row?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
}

fn main() -> Result<()> {
    // Same test call as in the JS code
    add_new_entry("XXXXX", 0)?;
    Ok(())
}