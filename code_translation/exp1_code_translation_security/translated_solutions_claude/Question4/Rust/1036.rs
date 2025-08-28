use rusqlite::{Connection, Result};

fn add_new_entry(name: &str, age: i32) -> Result<()> {
    let conn = Connection::open("database.db")?;

    // Prepare and execute the INSERT statement
    let mut stmt = conn.prepare("INSERT INTO STUDENTS VALUES (?,?)")?;
    stmt.execute([name, &age.to_string()])?;

    // Query and display all records
    let mut stmt = conn.prepare("SELECT rowid AS id, NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        Ok((
            row.get::<_, i64>(0)?,  // id
            row.get::<_, String>(1)?,  // NAME
            row.get::<_, i32>(2)?,     // AGE
        ))
    })?;

    for row in rows {
        let (id, name, age) = row?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
}

fn main() {
    if let Err(e) = add_new_entry("XXXXX", 0) {
        eprintln!("Error: {}", e);
    }
}