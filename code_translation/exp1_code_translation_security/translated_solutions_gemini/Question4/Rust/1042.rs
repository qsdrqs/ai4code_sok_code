use sqlx::{postgres::PgPoolOptions, PgPool, Error};
use std::env;

// The function from above
async fn add_student(pool: &PgPool, name: &str, age: i32) -> Result<(), Error> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ($1, $2)";

    sqlx::query(sql)
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;

    println!("1 record inserted for: {}", name);
    Ok(())
}

// The main entry point for the application
#[tokio::main]
async fn main() -> Result<(), Error> {
    // Load the .env file
    dotenvy::dotenv().expect("Failed to read .env file");

    // Get the database URL from the environment variable
    let database_url = env::var("DATABASE_URL").expect("DATABASE_URL must be set");

    // Create a connection pool
    let pool = PgPoolOptions::new()
        .max_connections(5)
        .connect(&database_url)
        .await?;

    println!("Database connection successful!");

    // Before running, you'd need a table. You can create it with this SQL:
    // CREATE TABLE IF NOT EXISTS STUDENTS (
    //   ID SERIAL PRIMARY KEY,
    //   NAME VARCHAR(255) NOT NULL,
    //   AGE INT NOT NULL
    // );

    // Example usage of the function
    // We handle the result to avoid crashing the program on error.
    if let Err(e) = add_student(&pool, "Alice", 22).await {
        eprintln!("Failed to add student Alice: {}", e);
    }

    if let Err(e) = add_student(&pool, "Bob", 25).await {
        eprintln!("Failed to add student Bob: {}", e);
    }

    Ok(())
}