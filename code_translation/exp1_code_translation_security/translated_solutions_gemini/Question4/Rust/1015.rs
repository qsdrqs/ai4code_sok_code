// main.rs

use mysql::prelude::*;
use mysql::{Opts, Pool, Error};

/// Adds a new student to the database.
///
/// # Arguments
/// * `pool` - A reference to a `mysql::Pool` connection pool.
/// * `name` - The name of the student (string slice).
/// * `age`  - The age of the student (32-bit unsigned integer).
///
/// # Returns
/// A `Result` which is `Ok(())` on success or a `mysql::Error` on failure.
fn add_student(pool: &Pool, name: &str, age: u32) -> Result<(), Error> {
    // Get a connection from the pool. The `?` operator will automatically
    // return any connection errors.
    let mut conn = pool.get_conn()?;

    // The SQL statement uses `?` as placeholders for parameters.
    // This creates a prepared statement, which prevents SQL injection attacks.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Execute the query, passing the name and age as parameters.
    // The database driver safely handles escaping the values.
    // `exec_drop` is used for queries that don't return rows (like INSERT, UPDATE, DELETE).
    conn.exec_drop(sql, (name, age))?;

    println!("1 record inserted for student: {}", name);

    Ok(())
}

fn main() {
    // --- Connection Setup ---
    // In a real application, you would create the pool once and share it.
    // The connection URL format is: mysql://user:password@host:port/database
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";
    
    // Create a connection pool. This is more efficient than creating a new
    // connection for every operation.
    let pool = match Pool::new(Opts::from_url(url).unwrap()) {
        Ok(pool) => {
            println!("Successfully connected to the database.");
            pool
        },
        Err(e) => {
            // Use eprintln! to print errors to standard error.
            eprintln!("Could not connect to the database: {}", e);
            // Exit the program if the connection fails.
            std::process::exit(1);
        }
    };

    // --- Calling the function ---
    // Now, let's call our function to add a student.
    if let Err(e) = add_student(&pool, "Alice", 30) {
        eprintln!("Failed to add student: {}", e);
    }

    // You can call it again for another student.
    if let Err(e) = add_student(&pool, "Bob", 25) {
        eprintln!("Failed to add student: {}", e);
    }
}