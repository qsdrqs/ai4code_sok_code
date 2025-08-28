use rusqlite::{params, Connection, Result};

/// Given an SQL table named "STUDENTS" with columns "NAME" and "AGE",  
/// add a new entry.
///
/// Arguments
/// * `name` – value for the "NAME" column.
/// * `age`  – value for the "AGE" column.
/// * `db`   – an open rusqlite `Connection`.
pub fn add_entry(name: &str, age: i32, db: &Connection) -> Result<()> {
    db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}