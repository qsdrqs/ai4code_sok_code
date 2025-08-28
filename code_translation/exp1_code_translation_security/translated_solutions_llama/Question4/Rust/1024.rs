use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold database connection settings
struct DbConfig {
    host: String,
    user: String,
    password: String,
    database: String,
}

#[tokio::main]
async fn main() -> Result<(), mysql::Error> {
    // Database connection settings
    let db_config = DbConfig {
        host: "127.0.0.1".to_string(),
        user: "yourusername".to_string(),
        password: "yourpassword".to_string(),
        database: "mydb".to_string(),
    };

    // Establish a connection pool
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some(&db_config.host))
        .user(Some(&db_config.user))
        .pass(Some(&db_config.password))
        .db_name(Some(&db_config.database));

    let pool = Pool::new(opts)?;

    // Get a connection from the pool
    let conn: PooledConn = pool.get_conn().await?;

    // SQL query
    let sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

    // Values to insert
    let values = vec![
        ("XXXXX".to_string(), "XXXXX".to_string()),
        // Add more values as needed
    ];

    // Execute the query
    let result = conn.prep_exec(sql, values).await?;

    // Print the number of affected rows
    println!("Number of records inserted: {}", result.affected_rows());

    Ok(())
}