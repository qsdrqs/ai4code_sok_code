// Cargo.toml dependency:
// rusqlite = "0.31"

use rusqlite::{named_params, Connection, Result};

#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

fn insert_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute(
        "INSERT INTO students (name, age) VALUES ($name, $age)",
        named_params! {
            "$name": name,
            "$age": age,
        },
    )?;
    Ok(())
}

fn main() -> Result<()> {
    let conn = Connection::open_in_memory()?;

    conn.execute("CREATE TABLE students (name TEXT, age INT)", [])?;

    insert_student(&conn, "XXXXX", 0)?;

    let mut stmt = conn.prepare("SELECT name, age FROM students")?;
    let rows = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age: row.get(1)?,
        })
    })?;

    for row in rows {
        println!("{:?}", row?);
    }

    // Connection is automatically closed when `conn` is dropped at end of scope
    Ok(())
}