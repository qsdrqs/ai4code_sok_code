use mysql::{OptsBuilder, PooledConn, Pool};
use std::error::Error;

// Define a struct to hold database configuration
struct Config {
    host: String,
    user: String,
    password: String,
    database: String,
}

// Function to create a database connection pool
async fn create_pool(config: &Config) -> Result<Pool, Box<dyn Error>> {
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some(&config.host))
        .user(Some(&config.user))
        .pass(Some(&config.password))
        .db_name(Some(&config.database));

    let pool = Pool::new(opts)?;
    Ok(pool)
}

// Function to insert data into the STUDENTS table
async fn insert_into(pool: &Pool, name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    let conn: PooledConn = pool.get_conn().await?;
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    conn.prep_exec(sql, (name, age)).await?;
    conn.close().await?;
    Ok(())
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    // Define database configuration
    let config = Config {
        host: "localhost".to_string(),
        user: "your_username".to_string(),
        password: "your_password".to_string(),
        database: "your_database".to_string(),
    };

    // Create a database connection pool
    let pool = create_pool(&config).await?;

    // Insert data into the STUDENTS table
    insert_into(&pool, "John Doe", 25).await?;

    Ok(())
}