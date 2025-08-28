// Import necessary items from the mysql crate
use mysql::prelude::*;
use mysql::{OptsBuilder, Pool, Error};

/// Inserts a new student record into the database.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` which is `Ok(())` on success or an `Err` containing a `mysql::Error` on failure.
fn inserter(name: &str, age: u32) -> Result<(), Error> {
    // 1. Configure the database connection options.
    // This is equivalent to the object passed to mysql.createConnection.
    let opts = OptsBuilder::new()
        .host(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("mydb"));

    // 2. Create a connection pool.
    // A pool is more robust than a single connection, as it handles reconnections automatically.
    let pool = Pool::new(opts)?;

    // 3. Get a connection from the pool.
    // The `?` operator will automatically return any connection error.
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    // 4. Define the SQL statement with placeholders (?) for security.
    // This prevents SQL injection attacks.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // 5. Execute the query.
    // We pass the actual values `name` and `age` in a tuple.
    // `exec_drop` is used for statements that don't return rows (like INSERT, UPDATE, DELETE).
    conn.exec_drop(sql, (name, age))?;

    println!("1 record inserted");

    // 6. If everything succeeded, return Ok.
    Ok(())
}

fn main() {
    // Example usage of the inserter function.
    // We call the function and handle the Result it returns.
    match inserter("Alice", 30) {
        Ok(_) => println!("Operation completed successfully."),
        Err(e) => eprintln!("An error occurred: {}", e),
    }
}