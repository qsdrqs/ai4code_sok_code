use rusqlite::{Connection, Result};

/// Adds a new student to the STUDENTS table.
///
/// # Arguments
///
/// * `conn` - A reference to the SQLite database connection.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
///
/// * `Result<usize>` - The number of rows affected by the insert operation.
pub fn add_student(conn: &Connection, name: &str, age: i32) -> Result<usize> {
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        (name, age),
    )
}

fn main() -> Result<()> {
    // Open or create the SQLite database file
    let conn = Connection::open("database.db")?;

    // Create the STUDENTS table if it doesn't already exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;

    // Add a new student
    let rows_affected = add_student(&conn, "XXXXX", 0)?;
    println!("Rows affected: {}", rows_affected);

    // Query and print all students
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;

    for row in rows {
        let (name, age) = row?;
        println!("Name: {}, Age: {}", name, age);
    }

    Ok(())
}