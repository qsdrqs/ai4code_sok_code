use sqlx::PgPool;

// Define a function to add a student
async fn add_student(pool: &PgPool, name: &str, age: i32) -> Result<(), sqlx::Error> {
    // SQL query to insert a student
    let query = "INSERT INTO students (name, age) VALUES ($1, $2)";

    // Execute the query
    sqlx::query(query)
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;

    Ok(())
}

// Example usage
#[tokio::main]
async fn main() -> Result<(), sqlx::Error> {
    // Database connection URL
    let database_url = "postgres://user:password@localhost:5432/database";

    // Create a database pool
    let pool = PgPool::connect(database_url).await?;

    // Add a student
    add_student(&pool, "John Doe", 20).await?;

    Ok(())
}