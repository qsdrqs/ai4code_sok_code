// Import necessary items from the mysql crate
use mysql::prelude::*;
use mysql::{params, OptsBuilder, Pool};

/// Inserts a new student record into the database.
///
/// # Arguments
/// * `pool` - A reference to the MySQL connection pool.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A Result indicating success or a mysql::Error on failure.
fn insert_student(pool: &Pool, name: &str, age: u32) -> Result<(), mysql::Error> {
    // Get a connection from the pool.
    // The `?` operator will propagate any errors, returning them from the function.
    let mut conn = pool.get_conn()?;

    // The SQL statement uses `?` as placeholders for parameters.
    // This creates a prepared statement, which is safe from SQL injection.
    let sql = "INSERT INTO students (name, age) VALUES (:name, :age)";

    // Execute the statement with the provided parameters.
    // `exec_drop` is used for statements that don't return rows (like INSERT, UPDATE, DELETE).
    conn.exec_drop(
        sql,
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    // If we reach here, the query was successful.
    println!("Successfully inserted student: {}", name);

    // In Rust, the connection `conn` is automatically returned to the pool
    // when it goes out of scope at the end of the function. No `end()` call is needed.
    Ok(())
}

fn main() {
    // --- Configuration ---
    // Replace with your actual database credentials.
    // It's better to load these from environment variables or a config file in a real app.
    let db_url = "mysql://your_user:your_password@localhost:3306/your_database";

    // Create a connection pool. A pool is the standard way to manage database
    // connections in a robust application.
    let pool = match Pool::new(OptsBuilder::from_opts(db_url)) {
        Ok(p) => p,
        Err(e) => {
            // Use eprintln! to print errors to standard error.
            eprintln!("Failed to create database connection pool: {}", e);
            return; // Exit if we can't connect.
        }
    };

    // --- Function Call ---
    // Call the function to insert a new student.
    // We handle the Result to check if the operation was successful.
    if let Err(e) = insert_student(&pool, "Alice", 22) {
        eprintln!("Failed to insert student: {}", e);
    }

    if let Err(e) = insert_student(&pool, "Bob", 25) {
        eprintln!("Failed to insert student: {}", e);
    }
}