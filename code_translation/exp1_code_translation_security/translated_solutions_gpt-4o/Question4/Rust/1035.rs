use rusqlite::{params, Connection, Result};

fn main() -> Result<()> {
    // Open a connection to the database
    let conn = Connection::open("studentdb")?;

    // Create the STUDENTS table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INT
        )",
        [],
    )?;

    // Function to insert a student into the STUDENTS table
    fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
        let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")?;
        stmt.execute(params![name, age])?;
        Ok(())
    }

    // Insert students
    insert(&conn, "Foo", 42)?;
    insert(&conn, "Bar", 55)?;

    // Uncomment the following block to print all students
    /*
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for student in student_iter {
        println!("{:?}", student?);
    }
    */

    // Close the connection
    Ok(())
}