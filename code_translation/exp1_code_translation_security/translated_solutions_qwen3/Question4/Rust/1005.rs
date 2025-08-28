use mysql::*;
use mysql::conn::Conn;
use mysql::Opts;

fn add_student(name: &str, age: u32) {
    // Step 1: Create a new connection object (not connected yet)
    let opts = Opts::from_url("mysql://user:password@localhost:3306/database")
        .expect("Failed to parse connection URL");

    let mut conn = Conn::new(opts)
        .expect("Failed to create connection object");

    // Step 2: Connect to the database
    conn.connect()
        .expect("Failed to connect to the database");

    // Step 3: Build the SQL query with string interpolation
    let query = format!("INSERT INTO STUDENTS VALUES ({}, {})", name, age);

    // Step 4: Execute the query and ignore any errors
    let _ = conn.query_drop(query);
}