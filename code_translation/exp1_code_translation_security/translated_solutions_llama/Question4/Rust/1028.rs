use mysql::{OptsBuilder, PooledConn};
use tokio;

// Define a function to add a student
async fn add_student(pool: &PooledConn, name: &str, age: u8) -> Result<(), mysql::Error> {
    let sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    pool.prep_exec(sql, (name, age))?;
    println!("1 record inserted");
    Ok(())
}

#[tokio::main]
async fn main() {
    // Establish a database connection
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .db_name(Some("your_database"))
        .pass(Some("your_password"));

    let pool = mysql::Pool::new(opts).await.unwrap();

    // Add a student
    let name = "John Doe";
    let age = 20;
    add_student(&pool.get_conn().await.unwrap(), name, age).await.unwrap();
}