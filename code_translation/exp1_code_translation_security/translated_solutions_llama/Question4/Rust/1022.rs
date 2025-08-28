use rusqlite::{Connection, Result};

// Function to write student data to the database
fn student_write(db: &Connection, name: &str, age: i32) -> Result<()> {
    db.execute(
        "INSERT INTO STUDENTS(NAME, AGE) VALUES(?1, ?2)",
        &[name, &age.to_string()],
    )?;
    Ok(())
}

fn main() -> Result<()> {
    // Open a connection to the database
    let conn = Connection::open("/home/codex/Documents/User_Study_UI/database.db")?;

    // Write student data
    student_write(&conn, "XXXXX", 0)?;

    // Read all student data
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let mut rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;
        Ok((name, age))
    })?;

    // Print student data
    while let Some(row) = rows.next()? {
        let (name, age) = row?;
        println!("Name: {}, Age: {}", name, age);
    }

    Ok(())
}