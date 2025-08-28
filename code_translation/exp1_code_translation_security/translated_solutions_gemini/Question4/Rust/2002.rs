// Use statements to bring necessary items into scope.
// The prelude brings in common traits like `Queryable`.
use mysql::prelude::*;
use mysql::{Conn, OptsBuilder, Pool};

/// Inserts a new student into the `STUDENTS` table.
///
/// # Arguments
///
/// * `conn`: A mutable reference to a MySQL connection.
/// * `name`: The name of the student (string slice).
/// * `age`: The age of the student (32-bit unsigned integer).
///
/// # Returns
///
/// * A `Result` which is `Ok(())` on success, or a `mysql::Error` on failure.
fn add_student(conn: &mut Conn, name: &str, age: u32) -> mysql::Result<()> {
    // The SQL query with '?' placeholders for parameters.
    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // `exec_drop` is used for queries where you don't need to process the result set,
    // like INSERT, UPDATE, or DELETE. It's the most direct equivalent to the
    // JavaScript "fire-and-forget" call.
    // The parameters are passed as a tuple.
    // The `?` operator will automatically propagate any errors, returning an `Err`
    // from the function if the database call fails.
    conn.exec_drop(query, (name, age))?;

    // If `exec_drop` was successful, we return `Ok(())` to signal success.
    // The `()` type is an empty tuple, often called "unit", used when there's
    // no data to return on success.
    Ok(())
}

// An example main function to show how to use `add_student`.
fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 1. Set up the database connection options.
    //    Replace with your actual database credentials.
    let opts = OptsBuilder::new()
        .user(Some("root"))
        .pass(Some("password"))
        .db_name(Some("my_db"));

    // 2. Create a connection pool. A pool is the recommended way to manage connections.
    let pool = Pool::new(opts)?;

    // 3. Get a connection from the pool.
    let mut conn = pool.get_conn()?;
    println!("Successfully connected to the database.");

    // 4. (Optional but recommended for a runnable example)
    //    Create the table if it doesn't exist.
    conn.query_drop(
        r"CREATE TABLE IF NOT EXISTS STUDENTS (
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            age INT
        )",
    )?;
    println!("`STUDENTS` table is ready.");

    // 5. Call our translated function.
    let student_name = "Alice";
    let student_age = 22;
    match add_student(&mut conn, student_name, student_age) {
        Ok(_) => println!("Successfully added student: {}", student_name),
        Err(e) => eprintln!("Error adding student: {}", e),
    }

    // Add another student
    if let Err(e) = add_student(&mut conn, "Bob", 35) {
        eprintln!("Failed to add Bob: {}", e);
    } else {
        println!("Successfully added student: Bob");
    }

    Ok(())
}