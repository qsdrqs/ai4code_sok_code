// main.rs
use rusqlite::{params, Connection, Result};

fn main() -> Result<()> {
    create_database()?;
    Ok(())
}

// Corresponds to the IIFE body in JS: creates the DB
fn create_database() -> Result<()> {
    println!("created database");

    // Open (or create) temp.db
    let conn = Connection::open("temp.db")?;

    // Continue exactly like the JS callback chain
    create_tables2(&conn)?;

    Ok(())
}

// JS: `createTables(db)` – not used directly but kept for completeness
#[allow(dead_code)]
fn create_tables(conn: &Connection) -> Result<()> {
    println!("pre-created tables");
    conn.execute(
        "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)",
        [],
    )?;
    Ok(())
}

// JS: `createTables2(db)` – creates the table THEN inserts a row
fn create_tables2(conn: &Connection) -> Result<()> {
    println!("pre-created tables");
    conn.execute(
        "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)",
        [],
    )?;

    // After the table exists, insert the demo row
    insert(conn, "John Doe", 30)?;

    Ok(())
}

// JS: `insert(db, name, age)`
fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
    let sql = "INSERT INTO students(name, age) VALUES (?1, ?2)";
    conn.execute(sql, params![name, age])?;
    println!("row added successfully");
    Ok(())
}