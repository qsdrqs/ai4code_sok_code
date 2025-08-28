use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a function to add a student
async fn add_student(pool: &Pool, name: &str, age: i32) -> Result<(), mysql::Error> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);

    let mut conn = pool.get_conn().await?;
    conn.query(sql).await?;

    Ok(())
}

#[tokio::main]
async fn main() {
    // Establish a database connection
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"));

    let pool = Pool::new(opts).await.unwrap();

    // Add a student
    let result = add_student(&pool, "XXXXX", 0).await;

    match result {
        Ok(_) => println!("Student added successfully"),
        Err(err) => println!("Error adding student: {}", err),
    }
}