// All necessary dependencies are included here.
use mysql_async::prelude::*;
use mysql_async::{Error, Pool};

/// Creates a new entry in the STUDENTS table.
///
/// # Arguments
/// * `pool` - A reference to the database connection pool.
/// * `name` - The name of the student.
/// * `age`  - The age of the student.
///
/// This function is asynchronous and returns a Result, which will be Ok(()) on success
/// or an Err containing a database error on failure.
async fn create_entry(pool: &Pool, name: &str, age: u32) -> Result<(), Error> {
    // 1. Get a connection from the pool.
    // The `?` operator will automatically return an error if getting a connection fails.
    let mut conn = pool.get_conn().await?;

    // 2. Define the SQL query with placeholders.
    let sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

    // 3. Execute the query.
    // `exec_drop` is used for queries that don't return rows (like INSERT, UPDATE, DELETE).
    // The parameters `(name, age)` are passed safely, preventing SQL injection.
    // The `await` keyword pauses execution until the database call is complete.
    // The `?` operator will handle any errors from the query itself.
    conn.exec_drop(sql, (name, age)).await?;

    // 4. If we reach this point, the query was successful.
    // The connection is automatically returned to the pool when `conn` goes out of scope.
    Ok(())
}

// An example `main` function to demonstrate how to use `create_entry`.
#[tokio::main]
async fn main() {
    // IMPORTANT: Replace with your actual database connection URL.
    // Format: "mysql://user:password@host:port/database"
    let db_url = "mysql://root:password@localhost:3306/my_database";

    // Create a connection pool. The pool is cheap to create and clone.
    let pool = Pool::new(db_url);

    // Example data
    let student_name = "Alice";
    let student_age = 22;

    println!(
        "Attempting to create entry for: {}, age {}",
        student_name, student_age
    );

    // Call our function and handle the result.
    match create_entry(&pool, student_name, student_age).await {
        Ok(_) => println!("Successfully created entry!"),
        Err(e) => eprintln!("Error creating entry: {}", e),
    }
}