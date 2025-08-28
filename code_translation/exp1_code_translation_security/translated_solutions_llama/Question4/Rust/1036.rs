use rusqlite::{Connection, Result};

fn add_new_entry(name: &str, age: i32) -> Result<()> {
    // Connect to the SQLite database
    let conn = Connection::open("database.db")?;

    // Execute SQL statements in a transaction
    conn.execute_batch(
        "BEGIN;
        CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER);
        INSERT INTO STUDENTS VALUES (?,?);
        COMMIT;",
        &[&name, &age],
    )?;

    // Query the database and print results
    let mut stmt = conn.prepare("SELECT rowid AS id, NAME, AGE FROM STUDENTS")?;
    let mut rows = stmt.query_map([], |row| {
        Ok((row.get(0)?, row.get(1)?, row.get(2)?))
    })?;

    while let Some(row) = rows.next()? {
        let (id, name, age): (i32, String, i32) = row?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
}

fn main() {
    if let Err(err) = add_new_entry("XXXXX", 0) {
        eprintln!("Error: {}", err);
    }
}