// All necessary dependencies are included via the `use` statements.
// These correspond to the libraries defined in Cargo.toml.
use sqlx::{Pool, Postgres};
use std::env;

/// Asynchronously adds a new student to the database.
///
/// # Arguments
/// * `pool` - A reference to the `sqlx` connection pool.
/// * `name` - The student's name.
/// * `age` - The student's age.
///
/// # Returns
/// A `Result` which is `Ok(())` on success, or an `Err` containing the error.
async fn add_student(pool: &Pool<Postgres>, name: &str, age: i32) -> anyhow::Result<()> {
    // This is a parameterized query.
    // The `sqlx::query!` macro checks the SQL syntax and parameter types at compile time.
    // `$1` and `$2` are placeholders for the parameters. This prevents SQL injection.
    let result = sqlx::query!(
        "INSERT INTO STUDENTS (name, age) VALUES ($1, $2)",
        name,
        age
    )
    .execute(pool) // Execute the query against the connection pool.
    .await?; // Await the async operation and propagate any errors with `?`.

    // The `execute` method returns a result containing information like rows affected.
    // We can check that exactly one row was changed.
    if result.rows_affected() == 1 {
        println!("1 record inserted");
    } else {
        // This case might occur in more complex scenarios, e.g., if the insert failed silently.
        println!("No records were inserted.");
    }

    Ok(()) // Return Ok to indicate success.
}

// The `tokio::main` attribute sets up the asynchronous runtime.
#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // For this example, we'll assume a `DATABASE_URL` environment variable is set.
    // e.g., DATABASE_URL="postgres://user:password@localhost/mydatabase"
    let database_url = env::var("DATABASE_URL")
        .expect("DATABASE_URL environment variable not set");

    // Create a connection pool. Pools are more efficient than creating new
    // connections for every query.
    let pool = Pool::<Postgres>::connect(&database_url).await?;

    // --- Example Usage ---
    // Let's add a student named "Alice" who is 22 years old.
    let student_name = "Alice";
    let student_age = 22;

    println!("Attempting to add student: {}, age: {}", student_name, student_age);

    // Call our function and handle any potential errors.
    if let Err(e) = add_student(&pool, student_name, student_age).await {
        eprintln!("Failed to add student: {}", e);
    }

    Ok(())
}