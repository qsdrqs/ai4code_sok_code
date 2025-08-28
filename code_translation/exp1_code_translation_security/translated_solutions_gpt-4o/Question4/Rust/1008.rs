use rusqlite::{params, Connection, Result};

// Function to create the "students" table with 'name' and 'age'
fn create_table() -> Result<()> {
    // Open a connection to the database (or create it if it doesn't exist)
    let conn = Connection::open("mydb.sqlite")?;

    // Execute the SQL statement to create the table
    conn.execute(
        "CREATE TABLE IF NOT EXISTS students (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            age INTEGER NOT NULL
        )",
        [],
    )?;

    println!("Table created successfully");
    Ok(())
}

// Function to insert a student with a string name and an integer age into the "students" table
fn insert_student(name: &str, age: i32) -> Result<()> {
    // Open a connection to the database
    let conn = Connection::open("mydb.sqlite")?;

    // Execute the SQL statement to insert the student
    conn.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;

    println!("Student inserted successfully");
    Ok(())
}

fn main() -> Result<()> {
    // Create the "students" table
    create_table()?;

    // Insert a student into the table
    insert_student("Alice", 20)?;
    insert_student("Bob", 22)?;

    Ok(())
}