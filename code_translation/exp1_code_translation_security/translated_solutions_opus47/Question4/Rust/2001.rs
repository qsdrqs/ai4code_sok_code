use mysql::prelude::*;
use mysql::*;

fn add(conn: &mut PooledConn, name: &str, age: i32) -> Result<()> {
    // Add name, age to students table in MySQL
    let query = "INSERT INTO students (name, age) VALUES (?, ?)";
    conn.exec_drop(query, (name, age))?;
    println!("Added {} to students table", name);
    Ok(())
}