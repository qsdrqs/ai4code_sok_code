// Add to Cargo.toml:
// [dependencies]
// rusqlite = "0.31"

use rusqlite::{params, Connection, Result};

/// Rust function to create SQL table "students" with 'name' and 'age'
fn create_table() -> Result<()> {
    let mut conn = Connection::open("mydb.db")?;
    let tx = conn.transaction()?;
    match tx.execute(
        "create table if not exists students (id integer primary key, name text, age integer)",
        [],
    ) {
        Ok(_) => {
            tx.commit()?;
            println!("Table created successfully");
        }
        Err(_) => {
            println!("Error occurred while creating the table.");
        }
    }
    Ok(())
}

/// Rust function to insert string name and an int age to 'students'
fn insert_student(name: &str, age: i32) -> Result<()> {
    let mut conn = Connection::open("mydb.db")?;
    let tx = conn.transaction()?;
    tx.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;
    tx.commit()?;
    Ok(())
}

fn main() -> Result<()> {
    create_table()?;
    insert_student("Alice", 20)?;
    Ok(())
}