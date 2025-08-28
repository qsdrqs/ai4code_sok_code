use mysql::*;
use mysql::prelude::*;

fn insert(name: String, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    // Create connection URL
    let url = "mysql://yourusername:yourpassword@localhost:3306/STUDENTS";
    
    // Create connection pool
    let pool = Pool::new(url)?;
    
    // Get connection from pool
    let mut conn = pool.get_conn()?;
    
    println!("Connected!");
    
    // Execute the INSERT query with parameters
    conn.exec_drop(
        "INSERT INTO students (name, age) VALUES (?, ?)",
        (name, age),
    )?;
    
    println!("1 record inserted");
    
    Ok(())
}

// Alternative version with error handling similar to the original JavaScript
fn insert_with_panic(name: String, age: i32) {
    let url = "mysql://yourusername:yourpassword@localhost:3306/STUDENTS";
    
    let pool = Pool::new(url).expect("Failed to create connection pool");
    let mut conn = pool.get_conn().expect("Failed to get connection");
    
    println!("Connected!");
    
    conn.exec_drop(
        "INSERT INTO students (name, age) VALUES (?, ?)",
        (name, age),
    ).expect("Failed to insert record");
    
    println!("1 record inserted");
}

// Example usage:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Using the version with proper error handling
    insert("John Doe".to_string(), 25)?;
    
    // Or using the version that panics on error (more similar to original JS)
    insert_with_panic("Jane Smith".to_string(), 23);
    
    Ok(())
}