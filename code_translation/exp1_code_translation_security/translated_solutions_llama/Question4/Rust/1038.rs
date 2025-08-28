use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold the student data
struct Student {
    name: String,
    age: u8,
}

// Define an async function to insert a student into the database
async fn insertion(name: String, age: u8) -> Result<(), mysql::Error> {
    // Create a connection pool
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .pass(Some("your_password"))
        .db_name(Some("your_database"));

    let pool = Pool::new(opts)?;

    // Get a connection from the pool
    let conn: PooledConn = pool.get_conn().await?;

    // Define the SQL query
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Execute the query
    tokio::spawn(async move {
        conn.prep_exec(sql, (name, age)).await?;
    });

    Ok(())
}

// Example usage
#[tokio::main]
async fn main() {
    let name = "John Doe".to_string();
    let age: u8 = 25;

    match insertion(name, age).await {
        Ok(_) => println!("Student inserted successfully"),
        Err(err) => println!("Error: {}", err),
    }
}