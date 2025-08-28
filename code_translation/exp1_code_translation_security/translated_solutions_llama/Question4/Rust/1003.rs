use rusqlite::{Connection, Error};

// Define a function to add a student to the database
fn add_student(db: &Connection, name: &str, age: i32) -> Result<(), Error> {
    db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        rusqlite::params![name, age],
    )?;
    Ok(())
}

// Example usage
fn main() -> Result<(), Error> {
    // Connect to the SQLite database
    let conn = Connection::open("students.db")?;

    // Create the STUDENTS table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INTEGER
        )",
        rusqlite::params![],
    )?;

    // Add a student
    add_student(&conn, "John Doe", 20)?;

    Ok(())
}