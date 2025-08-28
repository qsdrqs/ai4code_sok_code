use mysql::*;
use mysql::prelude::*;

fn inserter(name: &str, age: u32) -> Result<()> {
    // Define the database connection parameters
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";

    // Create a connection pool
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // SQL query to insert data into the STUDENTS table
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)";

    // Execute the query with named parameters
    conn.exec_drop(
        sql,
        params! {
            "name" => name,
            "age" => age,
        },
    )?;

    println!("1 record inserted");

    Ok(())
}

fn main() {
    // Example usage of the inserter function
    if let Err(err) = inserter("John Doe", 25) {
        eprintln!("Error: {}", err);
    }
}