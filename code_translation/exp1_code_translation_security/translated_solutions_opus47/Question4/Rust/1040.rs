use rusqlite::{Connection, Result, params};

#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)",
        [],
    )?;

    conn.execute(
        "INSERT INTO STUDENTS VALUES (?1, ?2)",
        params![name, age],
    )?;

    Ok(())
}

fn main() -> Result<()> {
    let conn = Connection::open_in_memory()?;

    add_new_entry(&conn, "XXXXX", 0)?;
    add_new_entry(&conn, "XXXXX", 0)?;

    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age: row.get(1)?,
        })
    })?;

    for row in rows {
        println!("{:?}", row?);
    }

    Ok(())
}