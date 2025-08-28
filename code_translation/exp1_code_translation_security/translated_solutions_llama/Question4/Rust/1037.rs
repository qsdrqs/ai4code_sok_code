use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define database configuration
const DB_HOST: &str = "localhost";
const DB_USER: &str = "your_username";
const DB_PASSWORD: &str = "your_password";
const DB_NAME: &str = "your_database";

// Define a function to insert a student
async fn insert_student(name: &str, age: u8) -> Result<(), mysql::Error> {
    // Create a database connection pool
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some(DB_HOST))
        .user(Some(DB_USER))
        .pass(Some(DB_PASSWORD))
        .db_name(Some(DB_NAME));

    let pool = Pool::new(opts)?;

    // Get a connection from the pool
    let conn: PooledConn = pool.get_conn().await?;

    // Insert statement (Note: The original JavaScript code was inserting hardcoded values.
    // This example uses the provided function parameters.)
    let sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

    // Execute the insert statement
    tokio::spawn(async move {
        conn.prep_exec(sql, (name, age)).await?;
        Ok(())
    })
    .await??;

    Ok(())
}

#[tokio::main]
async fn main() {
    // Example usage
    let result = insert_student("XXXXX", 0).await;
    match result {
        Ok(_) => println!("Student inserted successfully"),
        Err(e) => println!("Error inserting student: {}", e),
    }
}