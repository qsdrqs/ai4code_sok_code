// All necessary dependencies are included in the `use` statements.
// These would be configured in the Cargo.toml file (see explanation below).
use sqlx::sqlite::{SqlitePool, SqlitePoolOptions};
use sqlx::Error;

/// Represents the data for a student.
/// Using a struct is idiomatic in Rust for grouping related data.
struct Student<'a> {
    name: &'a str,
    age: i32,
}

/// Asynchronously registers a student in the database.
///
/// # Arguments
/// * `pool` - A reference to the database connection pool.
/// * `name` - The student's name.
/// * `age` - The student's age.
///
/// # Returns
/// A `Result` that is `Ok(())` on success or an `Err` with a database error.
async fn register_student(pool: &SqlitePool, name: &str, age: i32) -> Result<(), Error> {
    // In Rust, we don't need to create a separate struct just for the query,
    // but we can if it helps with organization.
    let student = Student { name, age };

    // The query uses `?` as placeholders for parameters.
    // This prevents SQL injection by separating the query logic from the data.
    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // `sqlx::query()` creates a prepared statement.
    // `.bind()` safely attaches the values to the placeholders.
    // `.execute()` runs the query against the database connection pool.
    // `.await` pauses execution until the database operation is complete.
    // The `?` at the end will automatically return any error that occurs.
    sqlx::query(query)
        .bind(student.name)
        .bind(student.age)
        .execute(pool)
        .await?;

    // If the query succeeds, return Ok with a unit type `()` to indicate success.
    Ok(())
}

// An example `main` function to demonstrate how to use `register_student`.
// `#[tokio::main]` sets up the asynchronous runtime.
#[tokio::main]
async fn main() -> Result<(), Error> {
    // 1. Create a connection pool.
    // For this example, we use an in-memory SQLite database.
    // The `?` operator handles potential connection errors.
    let pool = SqlitePoolOptions::new()
        .connect("sqlite::memory:")
        .await?;

    // 2. Create the STUDENTS table if it doesn't exist.
    sqlx::query(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            ID INTEGER PRIMARY KEY,
            NAME TEXT NOT NULL,
            AGE INTEGER NOT NULL
        )",
    )
    .execute(&pool)
    .await?;

    // 3. Call the function to register a new student.
    println!("Registering student: Alice, age 22...");
    if let Err(e) = register_student(&pool, "Alice", 22).await {
        eprintln!("Failed to register student: {}", e);
    } else {
        println!("Student 'Alice' registered successfully!");
    }
    
    println!("\nRegistering student: Bob, age 25...");
    register_student(&pool, "Bob", 25).await?;
    println!("Student 'Bob' registered successfully!");


    // 4. (Optional) Verify the data was inserted.
    let row: (String, i32) = sqlx::query_as("SELECT name, age FROM STUDENTS WHERE name = 'Alice'")
        .fetch_one(&pool)
        .await?;

    println!("\nVerification: Found student {} with age {} in the database.", row.0, row.1);

    Ok(())
}