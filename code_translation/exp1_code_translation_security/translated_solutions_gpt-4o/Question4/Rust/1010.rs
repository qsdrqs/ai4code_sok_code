use rusqlite::{params, Connection, Result};

// Define a Rust function that takes a String name and an i32 age and adds a new entry to the "STUDENTS" table.
// The function returns the number of rows affected.
fn add_student(conn: &Connection, name: &str, age: i32) -> Result<usize> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)";
    let result = conn.execute(sql, params![name, age])?;
    Ok(result)
}

fn main() -> Result<()> {
    // Create or open the SQLite database
    let conn = Connection::open("database.db")?;

    // Create the STUDENTS table (only do this once)
    // Uncomment the following lines if the table doesn't exist
    /*
    conn.execute(
        "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;
    */

    // Add a student to the database
    let rows_affected = add_student(&conn, "XXXXX", 0)?;
    println!("Number of rows affected: {}", rows_affected);

    // Display the contents of the STUDENTS table
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for student in student_iter {
        let (name, age) = student?;
        println!("Name: {}, Age: {}", name, age);
    }

    Ok(())
}