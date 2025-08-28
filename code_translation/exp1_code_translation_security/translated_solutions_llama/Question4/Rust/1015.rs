use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold database connection settings
struct DbConfig {
    host: String,
    user: String,
    password: String,
    database: String,
}

// Implement a function to add a student to the database
async fn add_student(name: &str, age: i32) -> Result<(), mysql::Error> {
    // Define database connection settings
    let db_config = DbConfig {
        host: "localhost".to_string(),
        user: "yourusername".to_string(),
        password: "yourpassword".to_string(),
        database: "mydb".to_string(),
    };

    // Create a connection pool
    let pool = Pool::new(
        OptsBuilder::new()
            .ip_or_hostname(Some(&db_config.host))
            .user(Some(&db_config.user))
            .pass(Some(&db_config.password))
            .db_name(Some(&db_config.database)),
    )?;

    // Get a connection from the pool
    let conn: PooledConn = pool.get_conn().await?;

    // Prepare and execute the SQL query
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    conn.prep_exec(sql, (&name, &age)).await?;

    println!("1 record inserted");

    Ok(())
}

#[tokio::main]
async fn main() {
    // Example usage
    if let Err(err) = add_student("John Doe", 20).await {
        eprintln!("Error: {}", err);
    }
}