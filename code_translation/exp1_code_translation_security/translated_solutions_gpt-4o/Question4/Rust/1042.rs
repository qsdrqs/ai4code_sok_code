use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: u32, pool: &Pool) -> Result<()> {
    // Create a connection from the pool
    let mut conn = pool.get_conn()?;

    // Prepare the SQL statement
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query_drop(sql)?;

    println!("1 record inserted");

    Ok(())
}

fn main() -> Result<()> {
    // Create a connection pool
    let url = "mysql://username:password@localhost:3306/database_name";
    let pool = Pool::new(url)?;

    // Call the function to add a student
    add_student("John Doe", 20, &pool)?;

    Ok(())
}