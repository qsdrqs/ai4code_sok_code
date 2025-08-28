use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a function to add a new student to the database
async fn add(pool: &Pool, name: &str, age: u8) -> Result<(), mysql::Error> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);

    let mut conn = pool.get_conn().await?;
    conn.query(sql).await?;
    println!("1 record inserted");

    Ok(())
}

#[tokio::main]
async fn main() {
    // Define database connection options
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .db_name(Some("your_database"))
        .pass(Some("your_password"));

    // Create a new pool
    let pool = Pool::new(opts).unwrap();

    // Example usage
    add(&pool, "John Doe", 20).await.unwrap();
}