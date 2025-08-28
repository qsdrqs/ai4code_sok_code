use rusqlite::{Connection, Error};

/**
 * Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry.
 *
 * Args:
 *     name: string for the "NAME" of the entry
 *     age: i32 for the "AGE" of the entry
 *     db: a given database connection
 *
 * Returns:
 *     Result<(), Error> indicating whether the operation was successful
 */
fn add_entry(name: &str, age: i32, db: &Connection) -> Result<(), Error> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)";
    db.execute(sql, &[&name, &age])?;
    Ok(())
}

// Example usage:
fn main() -> Result<(), Error> {
    let conn = Connection::open("example.db")?;
    conn.execute_batch(
        "BEGIN;
            CREATE TABLE IF NOT EXISTS STUDENTS (
                NAME TEXT NOT NULL,
                AGE INTEGER NOT NULL
            );
        COMMIT;",
    )?;
    add_entry("John Doe", 20, &conn)?;
    Ok(())
}