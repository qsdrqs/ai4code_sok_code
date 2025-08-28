// Import necessary modules from the mysql crate
use mysql::prelude::*;
use mysql::{Pool, QueryResult};

/// Function to generate an SQL INSERT statement for a student
fn add_student(name: &str, age: i32) -> String {
    let sql = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );
    println!("{}", sql);
    sql
}

fn main() {
    // Create a connection pool to the MySQL server
    let pool = Pool::new("mysql://yourusername:yourpassword@localhost")
        .expect("Failed to create connection pool");

    // Get a connection from the pool
    let mut conn = pool.get_conn()
        .expect("Failed to establish connection");

    // Log that we're connected
    println!("Connected!");

    // Generate and log the SQL statement
    let sql = add_student("XXXXX", 0);

    // Execute the SQL query and handle the result
    match conn.query(sql) {
        Ok(result) => println!("Result: {:?}", result),
        Err(e) => panic!("Error executing query: {:?}", e),
    }
}