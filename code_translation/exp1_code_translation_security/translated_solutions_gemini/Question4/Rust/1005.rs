// All dependencies are brought into scope.
use mysql::prelude::*;
use mysql::{Pool, OptsBuilder};
use lazy_static::lazy_static;
use std::env;

// Use lazy_static to create a single, thread-safe connection pool.
// This is better than creating a new connection for every function call.
lazy_static! {
    static ref CONN_POOL: Pool = {
        // In a real app, you would get these from environment variables or a config file.
        let user = env::var("DB_USER").unwrap_or_else(|_| "root".to_string());
        let pass = env::var("DB_PASS").unwrap_or_else(|_| "password".to_string());
        let host = env::var("DB_HOST").unwrap_or_else(|_| "127.0.0.1".to_string());
        let port = env::var("DB_PORT").unwrap_or_else(|_| "3306".to_string()).parse::<u16>().unwrap();
        let db_name = env::var("DB_NAME").unwrap_or_else(|_| "my_db".to_string());

        let opts = OptsBuilder::new()
            .user(Some(user))
            .pass(Some(pass))
            .ip_or_hostname(Some(host))
            .tcp_port(port)
            .db_name(Some(db_name));

        Pool::new(opts).expect("Failed to create a connection pool.")
    };
}

/// Adds a new student to the database.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or a `mysql::Error` on failure.
fn add_student(name: &str, age: u32) -> Result<(), mysql::Error> {
    // 1. Get a connection from the pool.
    // The `?` operator will automatically return the error if getting a connection fails.
    let mut conn = CONN_POOL.get_conn()?;

    // 2. Execute a prepared statement.
    // Using `?` as placeholders prevents SQL injection.
    // The `exec_drop` method is used for queries where we don't need to read the results (like INSERT).
    conn.exec_drop(
        r"INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    )?;

    // 3. If everything succeeded, return Ok.
    Ok(())
}

fn main() {
    println!("Attempting to add a student...");

    // Example usage of the add_student function.
    match add_student("Alice", 22) {
        Ok(_) => println!("Successfully added student 'Alice'."),
        Err(e) => eprintln!("Error adding student: {}", e),
    }

    match add_student("Bob", 25) {
        Ok(_) => println!("Successfully added student 'Bob'."),
        Err(e) => eprintln!("Error adding student: {}", e),
    }
}