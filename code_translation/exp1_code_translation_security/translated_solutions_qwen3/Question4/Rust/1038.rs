use mysql::prelude::*;
use mysql::{Pool, PooledConn};
use std::error::Error;

/// Inserts a student into the STUDENTS table.
/// 
/// # Arguments
/// 
/// * `name` - A string slice that holds the student's name.
/// * `age`  - An unsigned 32-bit integer representing the student's age.
/// 
/// # Returns
/// 
/// * `Ok(())` if the insertion was successful.
/// * `Err(Box<dyn Error>)` if any error occurred during the process.
fn insertion(name: &str, age: u32) -> Result<(), Box<dyn Error>> {
    // Create a connection pool to the MySQL database.
    // This assumes a local MySQL server running on port 3306 with no authentication.
    let pool = Pool::new("mysql://localhost:3306")?;

    // Get a connection from the pool.
    let mut conn = pool.get_conn()?;

    // Execute the INSERT statement using parameterized query to prevent SQL injection.
    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        (name, age),
    )?;

    Ok(())
}