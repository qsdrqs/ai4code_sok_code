// Add to Cargo.toml:
// [dependencies]
// mysql = "24.0"

use mysql::*;
use mysql::prelude::*;

// Create config and connection to your respective database then use the function:
// let config = "mysql://user:password@host:port/database";
// let pool = Pool::new(config).unwrap();
// let connection = pool.get_conn().unwrap();

fn insert_into(mut connection: Conn, name: &str, age: i32) -> Result<()> {
    let sql = format!(
        "INSERT INTO STUDENTS(NAME,AGE)
            VALUES ({}, {})",
        name, age
    );
    connection.query_drop(sql)?;
    // In Rust, the connection is automatically closed when it goes out of scope (drops)
    // which is equivalent to JavaScript's connection.end()
    Ok(())
}