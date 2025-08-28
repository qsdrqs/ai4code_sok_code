use mysql::*;
use mysql::prelude::*;

fn add(name: &str, age: i32, connection: &mut PooledConn) -> Result<()> {
    // Add name, age to students table in MySQL
    let query = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    match connection.exec_drop(query, (name, age)) {
        Ok(_) => {
            println!("Added {} to students table", name);
            Ok(())
        }
        Err(err) => Err(err)
    }
}

// Alternative version with error propagation using ?
fn add_concise(name: &str, age: i32, connection: &mut PooledConn) -> Result<()> {
    let query = "INSERT INTO students (name, age) VALUES (?, ?)";
    connection.exec_drop(query, (name, age))?;
    println!("Added {} to students table", name);
    Ok(())
}