use rusqlite::{Connection, Result};
use std::process;

// Function to create a database and perform operations
fn create_database() -> Result<()> {
    println!("Creating database");

    // Connect to the SQLite database
    let conn = Connection::open("temp.db")?;

    // Create tables and perform operations
    create_tables(&conn)?;

    // Insert a record after a delay (simulated using std::thread::sleep)
    // Note: Rust's async/await is used for async operations, not std::thread::sleep
    // For async operations, consider using tokio or async-std
    std::thread::sleep(std::time::Duration::from_millis(500));
    insert(&conn, "John Doe", 30)?;

    // Retrieve and print records after a delay
    std::thread::sleep(std::time::Duration::from_millis(500));
    retrieve_records(&conn)?;

    Ok(())
}

// Function to create tables in the database
fn create_tables(conn: &Connection) -> Result<()> {
    println!("Creating tables");

    conn.execute_batch("
        CREATE TABLE IF NOT EXISTS students (
            name TEXT,
            age INT
        );
    ")?;

    Ok(())
}

// Function to insert a record into the database
fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
    println!("Inserting record");

    let mut stmt = conn.prepare("INSERT INTO students (name, age) VALUES (?, ?)")?;
    stmt.execute([name, age])?;

    println!("Record added successfully");

    Ok(())
}

// Function to retrieve and print records from the database
fn retrieve_records(conn: &Connection) -> Result<()> {
    println!("Retrieving records");

    let mut stmt = conn.prepare("SELECT * FROM students")?;
    let mut rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;

        Ok((name, age))
    })?;

    while let Some(row) = rows.next()? {
        let (name, age) = row?;
        println!("{} {}", name, age);
    }

    Ok(())
}

fn main() {
    if let Err(err) = create_database() {
        eprintln!("Error: {}", err);
        process::exit(1);
    }
}