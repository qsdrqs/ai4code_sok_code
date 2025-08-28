use mysql::*;
use mysql::prelude::*;

fn add_student(name: &str, age: u32) -> Result<()> {
    // Establish a connection to the database
    let url = "mysql://username:password@localhost:3306/database_name"; // Replace with your database credentials
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Prepare the SQL query
    let sql = format!("INSERT INTO STUDENTS (name, age) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query_drop(sql)?;

    println!("1 record inserted");
    Ok(())
}