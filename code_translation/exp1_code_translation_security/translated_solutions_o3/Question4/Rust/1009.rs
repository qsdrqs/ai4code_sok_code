use sqlx::{mysql::MySqlPoolOptions, MySqlPool};

/// Inserts one row into STUDENTS (name, age).
///
/// Returns `Ok(())` on success, or the underlying `sqlx::Error` on failure.
pub async fn create_entry(pool: &MySqlPool, name: &str, age: i32) -> Result<(), sqlx::Error> {
    // The `?` operator propagates any database error upward,
    // similar to `throw` in the original JS code.
    sqlx::query("INSERT INTO STUDENTS (name, age) VALUES (?, ?)")
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;
    Ok(())
}

#[tokio::main]
async fn main() -> Result<(), sqlx::Error> {
    // In real code you would usually keep this pool in a global
    // or pass it around instead of recreating it each call.
    let pool = MySqlPoolOptions::new()
        .max_connections(5)
        .connect("mysql://user:password@localhost:3306/dbname")
        .await?;

    // Example usage; any error will bubble up and abort like
    // the `throw` in the JS version.
    create_entry(&pool, "Alice", 23).await?;

    Ok(())
}