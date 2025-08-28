use rusqlite::{params, Connection, Result};

/// Adds a new entry to the STUDENTS table.
///
/// # Arguments
/// * `name` - A string slice representing the name of the student.
/// * `age` - An integer representing the age of the student.
/// * `db` - A reference to a SQLite database connection.
///
/// # Returns
/// * `Ok(())` if the entry was successfully added.
/// * `Err(e)` if an error occurred during the database operation.
pub fn add_entry(name: &str, age: i32, db: &Connection) -> Result<()> {
    db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}