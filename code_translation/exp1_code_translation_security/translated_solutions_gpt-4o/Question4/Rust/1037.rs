use mysql::*;
use mysql::prelude::*;

fn insert_student(name: &str, age: u32) -> Result<()> {
    // Define the database connection configuration
    let url = "mysql://username:password@localhost:3306/database_name"; // Replace with your actual database URL
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Insert statement
    let sql = r"INSERT INTO STUDENTS(title, completed)
                VALUES('Learn how to insert a new row', true)";

    // Execute the insert statement
    conn.query_drop(sql)?;

    Ok(())
}

fn main() {
    if let Err(e) = insert_student("XXXXX", 0) {
        eprintln!("Error inserting student: {}", e);
    }
}