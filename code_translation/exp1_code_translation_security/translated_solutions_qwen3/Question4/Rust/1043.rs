// Import necessary crates
use mysql::prelude::*;
use mysql::{Conn, Opts, OptsBuilder};
use std::sync::Mutex;
use lazy_static::lazy_static;

// Define a global connection using lazy_static and Mutex
lazy_static! {
    static ref CONN: Mutex<Conn> = Mutex::new({
        // Replace with your actual database credentials
        let opts = OptsBuilder::new()
            .ip_or_hostname(Some("localhost"))
            .tcp_port(3306)
            .user(Some("root"))
            .pass(Some("password"))
            .db_name(Some("school_db"));

        Conn::new(opts).expect("Failed to connect to the database")
    });
}

/// Adds a new student to the STUDENTS table
pub fn add(name: &str, age: i32) {
    // Format the SQL query using string interpolation
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    
    // Log the SQL query
    println!("{}", sql);

    // Execute the query
    CONN.lock()
        .unwrap()
        .query_drop(sql)
        .expect("Query execution failed");

    // Log success
    println!("1 record inserted");
}