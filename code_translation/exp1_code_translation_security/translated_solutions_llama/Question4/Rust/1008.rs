use rusqlite::{Connection, Result};

// Function to create the "students" table
fn create_table(db_path: &str) -> Result<()> {
    let conn = Connection::open(db_path)?;
    conn.execute_batch(
        "BEGIN;
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY,
                name TEXT,
                age INTEGER
            );
        COMMIT;",
    )?;
    println!("Table created successfully");
    Ok(())
}

// Function to insert a student into the "students" table
fn insert_student(db_path: &str, name: &str, age: i32) -> Result<()> {
    let conn = Connection::open(db_path)?;
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        &[name, &age.to_string()],
    )?;
    println!("Student inserted successfully");
    Ok(())
}

fn main() {
    let db_path = "mydb.db";
    match create_table(db_path) {
        Ok(_) => println!("Table created successfully"),
        Err(e) => eprintln!("Error creating table: {}", e),
    }

    let name = "John Doe";
    let age = 20;
    match insert_student(db_path, name, age) {
        Ok(_) => println!("Student inserted successfully"),
        Err(e) => eprintln!("Error inserting student: {}", e),
    }
}