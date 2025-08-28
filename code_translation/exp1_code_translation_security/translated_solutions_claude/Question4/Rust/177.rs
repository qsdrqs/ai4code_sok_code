use rusqlite::{Connection, Result};

fn add_student(name: &str, age: i32) -> Result<()> {
    // Create/open database connection
    let conn = Connection::open("database.db")?;
    
    // Prepare and execute the INSERT statement
    // Using parameterized query to prevent SQL injection
    conn.execute(
        "INSERT INTO STUDENTS (name, age) VALUES (?1, ?2)",
        &[&name, &age.to_string()],
    )?;
    
    // Connection is automatically closed when it goes out of scope
    Ok(())
}

// Alternative version that more closely mirrors the original (not recommended due to SQL injection risk)
fn add_student_unsafe(name: &str, age: i32) -> Result<()> {
    let command = format!("INSERT INTO STUDENTS VALUES ('{}', '{}')", name, age);
    let conn = Connection::open("database.db")?;
    conn.execute(&command, [])?;
    Ok(())
}

// Example usage
fn main() -> Result<()> {
    add_student("John Doe", 25)?;
    Ok(())
}