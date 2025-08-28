// A robust and idiomatic Rust version of the JavaScript snippet.
// All dependencies are provided in the Cargo.toml file.

use mysql::prelude::*;
use mysql::{Pool, TxOpts};

fn main() -> Result<(), mysql::Error> {
    // The connection URL format is: mysql://user:password@host:port/database
    // Replace with your actual credentials.
    let url = "mysql://yourusername:yourpassword@127.0.0.1:3306/mydb";

    // Create a connection pool. This is a best practice for managing connections.
    let pool = Pool::new(url)?;

    // Get a single connection from the pool.
    // The `?` operator will propagate any connection errors.
    let mut conn = pool.get_conn()?;

    println!("Connected!");

    // Define the SQL statement with placeholders for a prepared statement.
    let sql = "INSERT INTO customers (name, address) VALUES (?, ?)";

    // Define the values to be inserted.
    // This is a Vec of tuples, where each tuple represents a row.
    let values = vec![
        ("John Doe", "123 Main St"),
        ("Jane Smith", "456 Oak Ave"),
        // Add more records here as needed
        // ("Another Name", "Another Address"),
    ];

    // Start a new transaction. This ensures that all records are inserted
    // successfully, or none are.
    let mut tx = conn.start_transaction(TxOpts::default())?;
    
    let mut total_affected_rows = 0;

    // To get the total number of affected rows, we iterate through the values
    // and execute the insert statement for each one within the transaction.
    for (name, address) in &values {
        // `exec_iter` returns a `QueryResult` which contains `affected_rows`.
        let result = tx.exec_iter(sql, (name, address))?;
        total_affected_rows += result.affected_rows();
    }

    // If all inserts were successful, commit the transaction.
    tx.commit()?;

    println!("Number of records inserted: {}", total_affected_rows);

    // The `main` function returns `Ok(())` on success.
    Ok(())
}