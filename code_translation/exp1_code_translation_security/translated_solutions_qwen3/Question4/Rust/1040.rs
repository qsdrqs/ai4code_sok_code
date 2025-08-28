use rusqlite::{Connection, Result, params};

/// Adds a new student entry to the database.
/// This function creates the table if it doesn't exist and inserts the given name and age.
fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // Create the table if it doesn't exist
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)",
        [],
    )?;

    // Prepare and execute the insert statement
    let mut stmt = conn.prepare("INSERT INTO STUDENTS VALUES (?, ?)")?;
    stmt.execute(params![name, age])?;

    Ok(())
}

fn main() {
    // Open an in-memory SQLite database
    let conn = Connection::open_in_memory().unwrap();

    // Insert two sample entries
    add_new_entry(&conn, "XXXXX", 0).unwrap();
    add_new_entry(&conn, "XXXXX", 0).unwrap();

    // Query and print all rows
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS").unwrap();
    let rows = stmt
        .query_map([], |row| {
            Ok((
                row.get::<_, String>("NAME").unwrap(),
                row.get::<_, i32>("AGE").unwrap(),
            ))
        })
        .unwrap();

    // Print each row in the same format as JavaScript's console.log(row)
    for row in rows {
        let (name, age) = row.unwrap();
        println!("{{ NAME: '{}', AGE: {} }}", name, age);
    }
}