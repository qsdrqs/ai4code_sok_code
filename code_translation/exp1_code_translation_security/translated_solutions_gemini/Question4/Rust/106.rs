use rusqlite::{Connection, Result, params};

/// Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry.
///
/// This function uses parameterized queries to prevent SQL injection.
///
/// # Args
/// * `name` - A string slice for the "NAME" of the entry.
/// * `age` - A 32-bit integer for the "AGE" of the entry.
/// * `db` - A reference to a rusqlite database connection.
///
/// # Returns
/// A `Result` indicating success (`Ok`) or the database error (`Err`).
fn add_entry(name: &str, age: i32, db: &Connection) -> Result<()> {
    // The SQL statement uses '?' as placeholders for parameters.
    // This is the standard way to prevent SQL injection.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)";

    // The `execute` method safely binds the `name` and `age` parameters to the '?' placeholders.
    // The `?` at the end propagates any potential errors.
    db.execute(sql, params![name, age])?;

    // If the execution was successful, return Ok.
    Ok(())
}

// --- Example Usage ---
fn main() -> Result<()> {
    // 1. Create an in-memory SQLite database for demonstration.
    let db = Connection::open_in_memory()?;

    // 2. Create the STUDENTS table.
    db.execute(
        "CREATE TABLE STUDENTS (
            id    INTEGER PRIMARY KEY,
            name  TEXT NOT NULL,
            age   INTEGER NOT NULL
        )",
        [], // No parameters for this query
    )?;

    // 3. Call our translated function to add a new student.
    let student_name = "Alice";
    let student_age = 22;
    println!("Adding student: {} (age {})", student_name, student_age);
    add_entry(student_name, student_age, &db)?;

    // 4. (Optional) Verify the entry was added correctly.
    let mut stmt = db.prepare("SELECT name, age FROM STUDENTS WHERE name = ?1")?;
    let (retrieved_name, retrieved_age): (String, i32) = stmt.query_row(params![student_name], |row| {
        Ok((row.get(0)?, row.get(1)?))
    })?;

    println!("Successfully retrieved: {} (age {})", retrieved_name, retrieved_age);
    assert_eq!(student_name, retrieved_name);
    assert_eq!(student_age, retrieved_age);

    Ok(())
}