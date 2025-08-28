/*
Is not TESTED!!
*/

use mysql::*;
use mysql::prelude::*;

fn main() -> Result<()> {
    // Define the database connection parameters
    let url = "mysql://yourusername:yourpassword@127.0.0.1:3306/mydb";

    // Create a connection pool
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    // Define the SQL query and values to insert
    let sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
    let values = vec![
        ("XXXXX", "XXXXX"),
    ];

    // Execute the query for each set of values
    for value in values {
        conn.exec_drop(sql, (value.0, value.1))?;
    }

    println!("Number of records inserted: {}", values.len());

    Ok(())
}