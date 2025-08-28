use rusqlite::{Connection, Result};

fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute_batch(
        "BEGIN;
        CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT);
        COMMIT;",
    )?;

    let mut stmt = conn.prepare("INSERT INTO STUDENTS VALUES (?, ?)")?;
    stmt.execute([name, age])?;

    Ok(())
}

fn main() -> Result<()> {
    let conn = Connection::open_in_memory()?;

    add_new_entry(&conn, "XXXXX", 0)?;
    add_new_entry(&conn, "XXXXX", 0)?;

    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let mut rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;
        Ok((name, age))
    })?;

    while let Some(row) = rows.next()? {
        let (name, age) = row?;
        println!("Name: {}, Age: {}", name, age);
    }

    Ok(())
}