use mysql::{Pool, PooledConn};
use mysql::prelude::Queryable;
use std::sync::OnceLock;

// Global connection pool initialized once
static POOL: OnceLock<Pool> = OnceLock::new();

// Function to insert a student into the database
fn add_student(name: &str, age: i32) {
    // Initialize the pool on first use
    let pool = POOL.get_or_init(|| {
        Pool::new("mysql://user:password@localhost:3306/mydatabase")
            .expect("Failed to create connection pool")
    });

    // Get a connection from the pool
    let mut conn = pool
        .get_conn()
        .expect("Failed to get a connection from the pool");

    // Execute the parameterized query
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    )
    .expect("Failed to insert record");

    // Log success
    println!("1 record inserted");
}