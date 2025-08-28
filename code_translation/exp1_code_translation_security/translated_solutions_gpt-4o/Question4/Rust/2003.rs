use rusqlite::{params, Connection, Result};

fn main() -> Result<()> {
    // Create an in-memory SQLite database
    let conn = Connection::open_in_memory()?;

    // Create the "students" table
    conn.execute(
        "CREATE TABLE students (name TEXT, age INT)",
        [],
    )?;

    // Function to insert a student into the database
    fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
        conn.execute(
            "INSERT INTO students (name, age) VALUES (?1, ?2)",
            params![name, age],
        )?;
        Ok(())
    }

    // Insert a student
    insert_student(&conn, "XXXXX", 0)?;

    // Query and print all rows from the "students" table
    let mut stmt = conn.prepare("SELECT name, age FROM students")?;
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age: row.get(1)?,
        })
    })?;

    for student in student_iter {
        println!("{:?}", student?);
    }

    Ok(())
}

// Struct to represent a student
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}