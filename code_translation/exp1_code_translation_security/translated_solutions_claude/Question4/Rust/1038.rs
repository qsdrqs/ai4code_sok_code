use mysql::*;
use mysql::prelude::*;

fn insertion(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    // Create connection URL (you'll need to provide actual connection details)
    let url = "mysql://username:password@localhost:3306/database_name";
    
    // Create connection pool
    let pool = Pool::new(url)?;
    
    // Get connection from pool
    let mut conn = pool.get_conn()?;
    
    // Execute the INSERT query with parameters
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    conn.exec_drop(sql, (name, age))?;
    
    Ok(())
}

// Example usage:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    insertion("John Doe", 25)?;
    println!("Record inserted successfully!");
    Ok(())
}