use mysql::prelude::*;
use mysql::{Pool, Connection};

/// Adds a new student to the STUDENTS table.
///
/// # Arguments
///
/// * `name` - A string slice that holds the student's name.
/// * `age` - An integer representing the student's age.
///
/// # Panics
///
/// This function will panic if the database query fails.
fn add_student(name: &str, age: i32) {
    // Construct the SQL INSERT statement using string formatting
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);

    // Establish a connection to the database
    let pool = Pool::new("mysql://user:password@localhost:3306/your_database").expect("Failed to create pool");
    let mut conn = pool.get_conn().expect("Failed to get connection from pool");

    // Execute the SQL query
    conn.query_drop(sql).expect("Query execution failed");

    // Log the success message
    println!("1 record inserted");
}