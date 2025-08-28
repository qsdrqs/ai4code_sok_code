use mysql::prelude::*;
use mysql::{Pool, Opts, Error};

/// Adds a new student to the database.
///
/// # Arguments
/// * `pool` - A reference to the MySQL connection pool.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// * A `Result` which is `Ok(())` on success or an `Err` containing the database error.
fn add(pool: &Pool, name: &str, age: u32) -> Result<(), Error> {
    // Get a connection from the pool. The `?` operator will automatically
    // return any error that occurs, propagating it to the caller.
    let mut conn = pool.get_conn()?;

    let query = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Execute the query with parameters.
    // `exec_drop` is used for queries that don't return rows (INSERT, UPDATE, DELETE).
    // Parameters are passed in a tuple `(name, age)` to prevent SQL injection.
    conn.exec_drop(query, (name, age))?;

    // If we reach this point, the query was successful.
    println!("Added {} to students table", name);

    // Return Ok to indicate success. The `()` is the "unit type", meaning
    // we are not returning any specific value on success.
    Ok(())
}

fn main() {
    // --- Database Connection Setup ---
    // Replace with your actual database connection details.
    let url = "mysql://user:password@localhost:3306/database_name";
    let opts = Opts::from_url(url).expect("Failed to parse database URL");
    let pool = Pool::new(opts).expect("Failed to create connection pool");

    // --- Calling the function ---
    let student_name = "Alice";
    let student_age = 22;

    // We must handle the Result returned by the `add` function.
    match add(&pool, student_name, student_age) {
        Ok(_) => println!("Successfully executed the add operation."),
        Err(e) => eprintln!("Error adding student: {}", e),
    }

    // Example with another student
    if let Err(e) = add(&pool, "Bob", 25) {
        eprintln!("Failed to add Bob: {}", e);
    }
}