use mysql::*;
use mysql::prelude::*;

fn create_entry(name: &str, age: i32, mut conn: PooledConn) -> Result<(), Box<dyn std::error::Error>> {
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    )?;
    
    Ok(())
}

// Alternative version using a connection pool
use mysql::{Pool, PooledConn};

fn create_entry_with_pool(name: &str, age: i32, pool: &Pool) -> Result<(), Box<dyn std::error::Error>> {
    let mut conn = pool.get_conn()?;
    
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (?, ?)",
        (name, age),
    )?;
    
    Ok(())
}

// Usage example:
fn main() -> Result<(), Box<dyn std::error::Error>> {
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    
    create_entry_with_pool("John Doe", 25, &pool)?;
    
    Ok(())
}