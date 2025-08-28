use mysql::{OptsBuilder, PooledConn, Pool};
use std::error::Error;

// Define a function to insert a record into the STUDENTS table
fn inserter(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    // Create a connection pool
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("mydb"));

    let pool = Pool::new(opts)?;

    // Get a connection from the pool
    let mut conn: PooledConn = pool.get_conn()?;

    // Insert a record into the STUDENTS table
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    conn.prep_exec(sql, (&name, &age))?;

    println!("1 record inserted");

    Ok(())
}

fn main() {
    // Example usage
    match inserter("John Doe", 25) {
        Ok(_) => println!("Record inserted successfully"),
        Err(err) => println!("Error: {}", err),
    }
}