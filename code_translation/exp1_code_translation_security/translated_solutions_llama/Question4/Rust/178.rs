use mysql::{Opts, PooledConn, Pool};
use mysql::prelude::*;

// Define a struct to represent a student
#[derive(Debug)]
struct Student {
    name: String,
    age: u8,
}

// Function to register a student
fn register_student(name: &str, age: u8, pool: &Pool) -> Result<(), mysql::Error> {
    let student = Student {
        name: name.to_string(),
        age,
    };

    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    let mut conn: PooledConn = pool.get_conn()?;
    conn.prep_exec(query, (&student.name, student.age))?;

    Ok(())
}

fn main() -> Result<(), mysql::Error> {
    // Define database connection options
    let opts = Opts::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("your_username"))
        .db_name(Some("your_database"))
        .pass(Some("your_password"));

    // Create a connection pool
    let pool = Pool::new(opts)?;

    // Register a student
    register_student("John Doe", 20, &pool)?;

    Ok(())
}