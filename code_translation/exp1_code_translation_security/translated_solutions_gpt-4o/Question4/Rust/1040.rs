use rusqlite::{params, Connection, Result};

fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // Create the table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INT
        )",
        [],
    )?;

    // Insert the new entry
    let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")?;
    stmt.execute(params![name, age])?;

    Ok(())
}

fn main() -> Result<()> {
    // Create an in-memory SQLite database
    let conn = Connection::open_in_memory()?;

    // Add new entries
    add_new_entry(&conn, "XXXXX", 0)?;
    add_new_entry(&conn, "XXXXX", 0)?;

    // Query and print all rows
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for row in rows {
        let (name, age) = row?;
        println!("NAME: {}, AGE: {}", name, age);
    }

    Ok(())
}