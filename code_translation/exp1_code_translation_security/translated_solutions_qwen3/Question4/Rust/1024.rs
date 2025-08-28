#[macro_use]
extern crate mysql;

use mysql::*;
use mysql::prelude::*;

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Connect to database
    let url = "mysql://yourusername:yourpassword@127.0.0.1:3306/mydb";
    let mut conn = Conn::new(url)?;
    println!("Connected!");

    // Insert data
    let sql = "INSERT INTO customers (name, address) VALUES ?";
    let values = Params::Multi(vec![
        params!["XXXXX", "XXXXX"],
    ]);

    conn.exec_drop(sql, values)?;
    let affected = conn.affected_rows();
    println!("Number of records inserted: {}", affected);

    Ok(())
}