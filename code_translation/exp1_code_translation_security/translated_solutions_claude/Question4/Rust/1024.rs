use mysql::*;
use mysql::prelude::*;

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Create connection URL
    let url = "mysql://yourusername:yourpassword@127.0.0.1:3306/mydb";
    
    // Create connection pool
    let pool = Pool::new(url)?;
    
    // Get connection from pool
    let mut conn = pool.get_conn()?;
    
    println!("Connected!");
    
    // SQL query
    let sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
    
    // Values to insert
    let values = vec![
        ("XXXXX", "XXXXX"),
    ];
    
    // Execute the query for each set of values
    let mut affected_rows = 0;
    for (name, address) in values {
        let result = conn.exec_drop(sql, (name, address))?;
        affected_rows += 1; // Since exec_drop doesn't return affected rows directly
    }
    
    println!("Number of records inserted: {}", affected_rows);
    
    Ok(())
}