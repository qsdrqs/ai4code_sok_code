use mysql::prelude::*;
use mysql::Connection;

fn create_entry(name: &str, age: i32) {
    // Define the connection URL (replace with actual credentials)
    let url = "mysql://user:password@localhost:3306/db";

    // Establish a new connection to the database
    let mut conn = Connection::new(url).expect("Failed to connect to the database");

    // Execute the INSERT query with the provided name and age
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    ).expect("Query execution failed");
}