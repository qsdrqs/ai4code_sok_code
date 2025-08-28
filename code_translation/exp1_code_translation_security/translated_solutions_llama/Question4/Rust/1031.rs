use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold student information
struct Student {
    name: String,
    age: i32,
}

// Define a function to insert a student into the database
async fn insert_student(name: &str, age: i32) -> Result<(), mysql::Error> {
    // Database connection settings
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("STUDENTS"));

    // Establish a connection pool
    let pool = Pool::new(opts)?;

    // Get a connection from the pool
    let conn: PooledConn = pool.get_conn().await?;

    // SQL query to insert a student
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Execute the query with the provided student information
    conn.prep_exec(sql, (&name, &age)).await?;

    println!("1 record inserted");

    Ok(())
}

#[tokio::main]
async fn main() {
    // Example usage
    let name = "John Doe";
    let age = 25;

    match insert_student(name, age).await {
        Ok(_) => println!("Student inserted successfully"),
        Err(err) => eprintln!("Error inserting student: {}", err),
    }
}