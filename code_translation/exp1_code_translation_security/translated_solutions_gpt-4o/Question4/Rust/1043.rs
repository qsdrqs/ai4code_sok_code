use mysql::*;
use mysql::prelude::*;
use std::error::Error;

fn add(name: &str, age: u32) -> Result<(), Box<dyn Error>> {
    // Create a connection to the database
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Create the SQL query
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{}", sql);

    // Execute the query
    conn.query_drop(sql)?;

    println!("1 record inserted");
    Ok(())
}

fn main() {
    // Example usage
    if let Err(e) = add("John Doe", 25) {
        eprintln!("Error: {}", e);
    }
}