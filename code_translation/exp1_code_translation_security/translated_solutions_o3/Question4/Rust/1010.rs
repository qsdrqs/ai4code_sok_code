// main.rs

use rusqlite::{params, Connection, Result};

/// Inserts a student (name, age) into the STUDENTS table.
///
/// Returns the number of rows affected (0 or 1).
fn add_student(conn: &Connection, name: &str, age: i32) -> Result<usize> {
    // The `?` operator bubbles any error back to the caller.
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )
}

fn main() -> Result<()> {
    // Create / open the database file.
    let conn = Connection::open("./database.db")?;

    // NOTE: run this only once; afterwards it is harmless because of IF NOT EXISTS.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)",
        params![],
    )?;

    // Example insertion
    let rows_added = add_student(&conn, "XXXXX", 0)?;
    println!("Rows inserted: {rows_added}");

    // Display all rows currently in the table.
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for student in student_iter {
        let (name, age) = student?;
        println!("{name} {age}");
    }

    // Connection is closed automatically when `conn` goes out of scope.
    Ok(())
}