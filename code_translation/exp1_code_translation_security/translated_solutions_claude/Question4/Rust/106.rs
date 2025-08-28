use rusqlite::{Connection, Result};

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
fn add_entry(name: &str, age: i32, db: &Connection) -> Result<()> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    db.execute(&sql, [])?;
    Ok(())
}