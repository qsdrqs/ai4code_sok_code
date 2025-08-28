use mysql::*;
use mysql::prelude::*;

fn insert(name: &str, age: i32) -> Result<()> {
    // Create a connection to the MySQL database
    let url = "mysql://yourusername:yourpassword@localhost:3306/STUDENTS";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // SQL query to insert data into the students table
    let sql = format!("INSERT INTO students (name, age) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query_drop(sql)?;

    println!("1 record inserted");

    Ok(())
}

fn main() {
    // Example usage of the insert function
    if let Err(err) = insert("John Doe", 25) {
        eprintln!("Error: {}", err);
    }
}