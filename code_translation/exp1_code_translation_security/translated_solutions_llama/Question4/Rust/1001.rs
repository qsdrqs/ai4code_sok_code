use rusqlite::{Connection, Result};

// Define a function to add a SQL entry
fn add_sql_entry(name: &str, age: i32) -> Result<()> {
    // Establish a connection to the database
    let conn = Connection::open("students.db")?;

    // Prepare the SQL statement
    let mut stmt = conn.prepare("INSERT INTO students (name, age) VALUES (?1, ?2)")?;

    // Execute the SQL statement
    stmt.execute([name, age])?;

    // Get the last inserted rowid
    let last_id = conn.last_insert_rowid();

    println!("User added with id: {}", last_id);

    Ok(())
}

fn main() {
    // Example usage
    match add_sql_entry("John Doe", 25) {
        Ok(_) => println!("Entry added successfully"),
        Err(err) => eprintln!("Error: {}", err),
    }
}