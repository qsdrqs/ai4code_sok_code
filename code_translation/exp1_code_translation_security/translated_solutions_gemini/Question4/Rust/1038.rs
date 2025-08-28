// Import necessary items from the crates.
use mysql::prelude::*;
use mysql::{params, Pool, Error};
use std::env;

/// Inserts a new student record into the database.
///
/// # Arguments
/// * `pool` - A reference to the database connection pool.
/// * `name` - The name of the student (as a string slice).
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or an `Err` containing a `mysql::Error` on failure.
fn insertion(pool: &Pool, name: &str, age: u32) -> Result<(), Error> {
    // Get a connection from the pool. The `?` operator will automatically
    // return any error that occurs, simplifying error handling.
    let mut conn = pool.get_conn()?;

    // The SQL query with named parameters (:name, :age) to prevent SQL injection.
    let sql = "INSERT INTO students (name, age) VALUES (:name, :age)";

    // Execute the query.
    // `exec_drop` is used for statements that don't return rows (like INSERT, UPDATE, DELETE).
    // The `params!` macro safely binds our variables to the named parameters in the query.
    conn.exec_drop(
        sql,
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    // If everything succeeded, return Ok. The `()` is the "unit type",
    // indicating no specific value is returned on success.
    Ok(())
}

fn main() {
    // Load environment variables from the .env file.
    dotenv::dotenv().expect("Failed to read .env file");

    // Get the database URL from the environment variables.
    let database_url = env::var("DATABASE_URL")
        .expect("DATABASE_URL must be set in .env file");

    // Create a new connection pool. A pool is a standard way to manage
    // database connections efficiently.
    let pool = Pool::new(database_url.as_str())
        .expect("Failed to create a database pool.");

    println!("Successfully connected to the database.");

    // --- Example Usage ---
    let student_name = "Alice";
    let student_age = 22;

    println!("Attempting to insert student: {} (age {})", student_name, student_age);

    // Call the insertion function and handle the result.
    match insertion(&pool, student_name, student_age) {
        Ok(_) => println!("Successfully inserted student record!"),
        Err(e) => eprintln!("Error inserting student record: {}", e),
    }
}