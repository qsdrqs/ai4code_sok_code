use mysql::*;
use mysql::prelude::*;

// Function to add a student
fn add_student(name: &str, age: i32) -> String {
    format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age)
}

fn main() -> Result<()> {
    // Create a connection to the database
    let url = "mysql://yourusername:yourpassword@localhost:3306";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Connect to the database
    println!("Connected!");

    // Execute the query
    let query = add_student("XXXXX", 0);
    conn.query_drop(query)?;

    println!("Student added successfully!");

    Ok(())
}