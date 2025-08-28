use rusqlite::{params, Connection, Result};

#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

/// Equivalent of the JavaScript `insertStudent` function.
fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}

fn main() -> Result<()> {
    // `:memory:` database (lives only for this process)
    let conn = Connection::open_in_memory()?;

    // CREATE TABLE students (name TEXT, age INT);
    conn.execute(
        "CREATE TABLE students (name TEXT, age INT)",
        [],                // no parameters
    )?;

    // INSERT a test row
    insert_student(&conn, "XXXXX", 0)?;

    // SELECT * FROM students;  -- and print every row
    let mut stmt = conn.prepare("SELECT name, age FROM students")?;
    let rows = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age:  row.get(1)?,
        })
    })?;

    for student in rows {
        println!("{:?}", student?);
    }

    // `conn` drops here and the in-memory DB is closed automatically.
    Ok(())
}