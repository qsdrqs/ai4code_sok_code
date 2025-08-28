use rusqlite::{Connection, Result};

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: String for the "NAME" of the entry
    age: i32 for the "AGE" of the entry
    conn: a given database connection
*/
fn add_entry(name: &str, age: i32, conn: &Connection) -> Result<()> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    conn.execute(&sql, [])?;
    Ok(())
}