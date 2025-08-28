use mysql::{OptsBuilder, PooledConn};
use tokio;

// Define a function to create a new entry in the STUDENTS table
async fn create_entry(name: &str, age: i32) -> Result<(), mysql::Error> {
    // Establish a database connection
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .pass(Some("your_password"))
        .db_name(Some("your_database"));

    let pool = mysql::Pool::new(opts)?;

    // Get a connection from the pool
    let mut conn: PooledConn = pool.get_conn().await?;

    // Execute the INSERT query
    conn.prep_exec("INSERT INTO STUDENTS (name, age) VALUES (?, ?)", (name, age)).await?;

    Ok(())
}

// Example usage
#[tokio::main]
async fn main() {
    match create_entry("John Doe", 25).await {
        Ok(_) => println!("Entry created successfully"),
        Err(err) => eprintln!("Error: {}", err),
    }
}