// Import necessary crates and macros
extern crate rusqlite;

use rusqlite::{Connection, Result};
use rusqlite::params;

fn main() -> Result<()> {
    // Open (or create) the SQLite database
    let conn = Connection::open("studentdb")?;

    // Create the STUDENTS table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        params![],
    )?;

    // Define an inline closure to insert records into the table
    let insert = |name: &str, age: i32| -> Result<()> {
        conn.execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
            params![name, age],
        )
    };

    // Insert two sample records
    insert("Foo", 42)?;
    insert("Bar", 55)?;

    // Optional: Uncomment to print all records from the STUDENTS table
    // let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    // let rows = stmt.query_map([], |row| {
    //     Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    // })?;
    //
    // for row in rows {
    //     let (name, age) = row?;
    //     println!("Name: {}, Age: {}", name, age);
    // }

    Ok(())
}