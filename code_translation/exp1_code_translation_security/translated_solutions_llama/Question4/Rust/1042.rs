use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a function to add a student
async fn add_student(pool: &Pool, name: &str, age: u8) -> Result<(), mysql::Error> {
    let conn: PooledConn = pool.get_conn().await?;
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    let params = (name, age);

    conn.prep_exec(sql, params).await?;

    println!("1 record inserted");

    Ok(())
}

// Main function
#[tokio::main]
async fn main() {
    // Define database connection options
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .db_name(Some("your_database"))
        .pass(Some("your_password"));

    // Create a pool
    let pool = Pool::new(opts).unwrap();

    // Add a student
    add_student(&pool, "John Doe", 20).await.unwrap();
}