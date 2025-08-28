use sqlx::{Pool, Sqlite, sqlite::SqlitePool};
use anyhow::Result;

/*
Assuming there's a SQL table called STUDENTS available.
The main function below will create it for this example.
*/

/// Adds a new entry to the SQL table "STUDENTS".
///
/// # Arguments
/// * `pool` - A reference to the sqlx connection pool.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Security Note
/// This function uses parameterized queries (`?` placeholders) to prevent
/// SQL injection attacks, which is a major improvement over string concatenation.
async fn add(pool: &Pool<Sqlite>, name: &str, age: i32) -> Result<()> {
    // The SQL query uses `?` as a placeholder for parameters.
    // This is the standard, safe way to pass data to a query.
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    println!("Executing SQL: {}", sql);
    println!("With parameters: name = \"{}\", age = {}", name, age);

    // `sqlx::query` creates a query object.
    // `.bind()` safely attaches the variables to the `?` placeholders.
    // `.execute()` runs the query against the database pool.
    // The `?` at the end will propagate any errors that occur.
    sqlx::query(sql)
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;

    println!("1 record inserted");
    
    // Return Ok to indicate success. The `()` means there's no value to return.
    Ok(())
}

#[tokio::main]
async fn main() -> Result<()> {
    // 1. Set up an in-memory SQLite database for this example.
    //    In a real application, you would connect to a persistent database.
    let pool = SqlitePool::connect("sqlite::memory:").await?;

    // 2. Create the STUDENTS table if it doesn't exist.
    sqlx::query(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            id INTEGER PRIMARY KEY,
            name TEXT NOT NULL,
            age INTEGER NOT NULL
        )"
    )
    .execute(&pool)
    .await?;

    // 3. Call the `add` function to insert a new student.
    println!("--- Calling add() function ---");
    add(&pool, "Alice", 30).await?;
    println!("----------------------------\n");

    // Example with another student
    println!("--- Calling add() function again ---");
    add(&pool, "Bob", 25).await?;
    println!("----------------------------------");

    Ok(())
}