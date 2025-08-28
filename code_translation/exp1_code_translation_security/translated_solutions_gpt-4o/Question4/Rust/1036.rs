use rusqlite::{params, Connection, Result};

fn add_new_entry(name: &str, age: i32) -> Result<()> {
    // Open a connection to the SQLite database
    let conn = Connection::open("database.db")?;

    // Insert the new entry into the STUDENTS table
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;

    // Query and print all rows in the STUDENTS table
    let mut stmt = conn.prepare("SELECT rowid, NAME, AGE FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((
            row.get::<_, i32>(0)?, // rowid
            row.get::<_, String>(1)?, // NAME
            row.get::<_, i32>(2)?, // AGE
        ))
    })?;

    for student in student_iter {
        let (id, name, age) = student?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
}

fn main() -> Result<()> {
    // Call the function with example data
    add_new_entry("XXXXX", 0)?;
    Ok(())
}