// main.rs
use rusqlite::{params, Connection, Result};

/// Insert a single student row (NAME, AGE) into the table.
fn student_write(conn: &Connection, name: &str, age: i32) -> Result<()> {
    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}

fn main() -> Result<()> {
    // Equivalent to: new sqlite3.Database('/home/codex/Documents/User_Study_UI/database.db')
    let conn = Connection::open("/home/codex/Documents/User_Study_UI/database.db")?;

    // INSERT row
    student_write(&conn, "XXXXX", 0)?;

    // SELECT * and print every row
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        // You can adapt the column list/types to your table definition.
        // Here we collect them as a Vec<String> for a generic “print everything” approach.
        let column_count = row.column_count();
        let mut cols = Vec::with_capacity(column_count);
        for i in 0..column_count {
            // Each column is read as String for simplicity.
            cols.push(row.get::<_, String>(i)?);
        }
        Ok(cols)
    })?;

    for row in rows {
        let row = row?;
        println!("{:?} {:?}", row, row);       // prints the row twice just like JS `console.log(row, row)`
    }

    Ok(())
}