use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold the student information
struct Student {
    name: String,
    age: u8,
}

// Define a function to add a student to the database
async fn add_student(pool: &Pool, student: Student) -> Result<(), mysql::Error> {
    let conn: PooledConn = pool.get_conn().await?;
    let query = "INSERT INTO students (name, age) VALUES (?, ?)";
    conn.prep_exec(query, (student.name, student.age)).await?;
    println!("Added {} to students table", student.name);
    Ok(())
}

// Define a main function to demonstrate the usage
#[tokio::main]
async fn main() {
    // Define database connection options
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .db_name(Some("your_database"))
        .pass(Some("your_password"));

    // Create a database pool
    let pool = Pool::new(opts).unwrap();

    // Create a student
    let student = Student {
        name: "John Doe".to_string(),
        age: 20,
    };

    // Add the student to the database
    if let Err(err) = add_student(&pool, student).await {
        eprintln!("Error adding student: {}", err);
    }
}