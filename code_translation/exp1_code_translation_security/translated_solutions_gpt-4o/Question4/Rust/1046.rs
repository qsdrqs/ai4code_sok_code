use mysql::*;
use mysql::prelude::*;

// Function to insert data into the database
fn insert_into(name: &str, age: u32) -> Result<()> {
    // Define the database connection URL (replace with your actual database credentials)
    let database_url = "mysql://username:password@localhost:3306/database_name";

    // Create a connection pool
    let pool = Pool::new(database_url)?;

    // Get a connection from the pool
    let mut conn = pool.get_conn()?;

    // Create the SQL query
    let sql = format!("INSERT INTO STUDENTS(NAME, AGE) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query_drop(sql)?;

    // Connection will be automatically closed when it goes out of scope
    Ok(())
}

fn main() {
    // Example usage of the `insert_into` function
    if let Err(e) = insert_into("John Doe", 25) {
        eprintln!("Error inserting data: {}", e);
    } else {
        println!("Data inserted successfully!");
    }
}