use rusqlite::{params, Connection, Result};

fn add_student(name: &str, age: i32) -> Result<()> {
    // Establish a connection to the database
    let conn = Connection::open("students.db")?;

    // Prepare the SQL command
    let command = "INSERT INTO STUDENTS (name, age) VALUES (?1, ?2)";

    // Execute the command with the provided parameters
    conn.execute(command, params![name, age])?;

    // Connection is closed automatically when it goes out of scope
    Ok(())
}

fn main() {
    // Example usage
    if let Err(err) = add_student("John Doe", 20) {
        eprintln!("Error adding student: {}", err);
    }
}