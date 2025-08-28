use mysql::prelude::*;
use mysql::{Conn, OptsBuilder};

/// Inserts a new student record into the STUDENTS table.
///
/// # Arguments
///
/// * `name` - A string slice that holds the student's name.
/// * `age`  - An unsigned 32-bit integer representing the student's age.
///
pub fn inserter(name: &str, age: u32) {
    // Configure the database connection
    let opts = OptsBuilder::new()
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .ip_or_hostname(Some("localhost"))
        .tcp_port(3306)
        .db_name(Some("mydb"));

    // Establish the connection
    let mut conn = Conn::new(&opts).expect("Failed to connect to the database");

    // Log successful connection
    println!("Connected!");

    // Define the SQL query with placeholders
    let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Execute the query with the provided parameters
    conn.exec_drop(query, (name, age))
        .expect("Failed to insert record");

    // Log successful insertion
    println!("1 record inserted");
}