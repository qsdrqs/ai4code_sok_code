use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: u32) -> Result<()> {
    // Define the database connection parameters
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";

    // Create a connection pool
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // SQL query to insert a new student
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query_drop(sql)?;

    println!("1 record inserted");

    Ok(())
}

fn main() {
    // Example usage
    if let Err(err) = add_student("John Doe", 20) {
        eprintln!("Error: {}", err);
    }
}