use mysql::*;
use mysql::prelude::*;

fn insertion(name: &str, age: u32) -> Result<()> {
    // Create a connection to the MySQL database
    let url = "mysql://username:password@localhost:3306/database_name"; // Replace with your actual database credentials
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // SQL query to insert data into the STUDENTS table
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)";

    // Execute the query with the provided parameters
    conn.exec_drop(sql, params! {
        "name" => name,
        "age" => age,
    })?;

    Ok(())
}