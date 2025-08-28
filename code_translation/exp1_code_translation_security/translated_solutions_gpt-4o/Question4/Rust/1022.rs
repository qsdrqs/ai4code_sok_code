use rusqlite::{params, Connection, Result};

fn main() -> Result<()> {
    // Open a connection to the SQLite database
    let conn = Connection::open("/home/codex/Documents/User_Study_UI/database.db")?;

    // Function to insert a student into the database
    fn student_write(conn: &Connection, name: &str, age: i32) -> Result<()> {
        conn.execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
            params![name, age],
        )?;
        Ok(())
    }

    // Insert a student
    student_write(&conn, "XXXXX", 0)?;

    // Query all rows from the STUDENTS table
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((
            row.get::<_, String>(0)?, // Assuming the first column is NAME
            row.get::<_, i32>(1)?,   // Assuming the second column is AGE
        ))
    })?;

    // Print each row
    for student in student_iter {
        let (name, age) = student?;
        println!("Name: {}, Age: {}", name, age);
    }

    Ok(())
}