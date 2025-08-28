// Import necessary items from the mysql crate
use mysql::prelude::*;
use mysql::{Opts, OptsBuilder, Conn};

/// Represents the configuration for the database connection.
/// In a real app, you would load this from a file or environment variables.
fn get_db_opts() -> Opts {
    // Replace with your actual database connection details.
    // The format is: "mysql://user:password@host:port/database"
    let url = "mysql://root:password@localhost:3306/my_database";
    Opts::from_url(url).expect("Could not parse database URL")
}

/// Inserts a new student record into the database.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or a `mysql::Error` on failure.
fn insert_into(name: &str, age: u32) -> Result<(), mysql::Error> {
    // Establish a new connection to the database.
    // The `?` operator will automatically return any connection errors.
    let mut conn = Conn::new(get_db_opts())?;

    // The SQL query with `?` placeholders to prevent SQL injection.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Execute the query with the provided parameters.
    // `exec_drop` is used for queries that don't return rows (INSERT, UPDATE, DELETE).
    // The `?` operator will automatically return any query execution errors.
    conn.exec_drop(sql, (name, age))?;

    // If we reach this point, the query was successful.
    // The connection will be automatically closed when `conn` goes out of scope.
    Ok(())
}

fn main() {
    // Example usage of the function.
    let student_name = "Alice";
    let student_age = 30;

    println!("Attempting to insert {} (age {})...", student_name, student_age);

    // We must handle the Result returned by the function.
    match insert_into(student_name, student_age) {
        Ok(_) => println!("Successfully inserted record!"),
        Err(e) => eprintln!("Failed to insert record: {}", e),
    }
}