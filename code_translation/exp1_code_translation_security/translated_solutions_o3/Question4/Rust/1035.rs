// main.rs
use rusqlite::{params, Connection, Result};

fn main() -> Result<()> {
    // Open (or create) the database file “studentdb”
    let conn = Connection::open("studentdb")?;

    // Create the table if it does not yet exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
             NAME TEXT,
             AGE  INTEGER
         )",
        [],
    )?;

    // Insert two example rows
    insert(&conn, "Foo", 42)?;
    insert(&conn, "Bar", 55)?;

    // (Connection is closed automatically when `conn` goes out of scope)
    Ok(())
}

/// Insert a single student row
fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
    let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)")?;
    stmt.execute(params![name, age])?;

    /*  // Uncomment to dump all rows after each insert
    let mut query = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = query.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for student in rows {
        println!("{:?}", student?);
    }
    */

    Ok(())
}