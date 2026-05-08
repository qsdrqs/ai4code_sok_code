// Cargo.toml dependency:
// [dependencies]
// rusqlite = "0.31"

use rusqlite::{params, Connection, Result};

fn add_new_entry(name: &str, age: i32) -> Result<()> {
    let conn = Connection::open("database.db")?;

    // Prepare and execute the INSERT statement.
    // The inner scope ensures `stmt` is dropped (finalized) before the next prepare.
    {
        let mut stmt = conn.prepare("INSERT INTO STUDENTS VALUES (?,?)")?;
        stmt.execute(params![name, age])?;
        // stmt is automatically finalized here when it goes out of scope
    }

    // Prepare and execute the SELECT statement, iterating over each row
    let mut stmt = conn.prepare("SELECT rowid AS id, NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        let id: i64 = row.get(0)?;
        let name: String = row.get(1)?;
        let age: i32 = row.get(2)?;
        Ok((id, name, age))
    })?;

    for row_result in rows {
        let (id, name, age) = row_result?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
    // conn is automatically closed when it goes out of scope (RAII)
}

fn main() -> Result<()> {
    add_new_entry("XXXXX", 0)?;
    Ok(())
}