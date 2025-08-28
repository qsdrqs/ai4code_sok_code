use rusqlite::{Connection, Result, params};

fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // Create table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)",
        [],
    )?;
    
    // Prepare and execute insert statement
    let mut stmt = conn.prepare("INSERT INTO STUDENTS VALUES (?, ?)")?;
    stmt.execute(params![name, age])?;
    
    Ok(())
}

fn main() -> Result<()> {
    // Create in-memory database
    let conn = Connection::open_in_memory()?;
    
    // Add entries
    add_new_entry(&conn, "XXXXX", 0)?;
    add_new_entry(&conn, "XXXXX", 0)?;
    
    // Query and print results
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((row.get::<_, String>(0)?, row.get::<_, i32>(1)?))
    })?;
    
    for row in rows {
        match row {
            Ok((name, age)) => println!("{{ NAME: '{}', AGE: {} }}", name, age),
            Err(e) => eprintln!("Error reading row: {}", e),
        }
    }
    
    Ok(())
}