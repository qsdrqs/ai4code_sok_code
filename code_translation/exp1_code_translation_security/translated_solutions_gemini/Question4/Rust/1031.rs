// Import necessary items from the mysql crate
use mysql::prelude::*;
use mysql::{OptsBuilder, Pool};

/// Inserts a new student record into the database.
///
/// # Arguments
/// * `pool` - A reference to the database connection pool.
/// * `name` - The name of the student (as a string slice).
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or a `mysql::Error` on failure.
fn insert(pool: &Pool, name: &str, age: u32) -> Result<(), mysql::Error> {
    // Get a connection from the pool. The `?` operator will automatically
    // return any error that occurs.
    let mut conn = pool.get_conn()?;

    println!("Connected successfully!");

    // The SQL query with placeholders `?` to prevent SQL injection.
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Execute the prepared statement.
    // `exec_drop` is used for queries where you don't need to retrieve rows (like INSERT, UPDATE, DELETE).
    // The parameters are passed in a tuple `(name, age)`.
    conn.exec_drop(sql, (name, age))?;

    // If we reach here, the query was successful.
    println!("1 record inserted for name: {}, age: {}", name, age);

    Ok(())
}

fn main() {
    // --- Database Connection Setup ---
    // This is similar to mysql.createConnection in JavaScript.
    // We use an OptsBuilder for a clean and type-safe configuration.
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("STUDENTS"));

    // Create a connection pool. A pool is a standard way to manage database connections
    // for better performance and reliability.
    let pool = match Pool::new(opts) {
        Ok(pool) => pool,
        Err(e) => {
            // If the connection fails, print an error and exit.
            eprintln!("Failed to create database pool: {}", e);
            return;
        }
    };

    // --- Calling the insert function ---
    // Now, let's call our function with some sample data.
    if let Err(e) = insert(&pool, "Alice", 22) {
        eprintln!("Failed to insert record: {}", e);
    }

    // You can call it again with different data.
    if let Err(e) = insert(&pool, "Bob", 35) {
        eprintln!("Failed to insert record: {}", e);
    }
}