extern crate mysql;

use mysql::prelude::*;
use mysql::{Conn, Opts, Error};

/// Inserts a new row into the `todos` table with the given `name` and `age`.
/// The `table` parameter is unused, as in the original JavaScript code.
pub fn add(name: &str, age: i32, _table: &str) {
    // Hardcoded database connection URL
    let url = "mysql://root:password@localhost:3306/my_database";

    // Parse the connection URL
    let opts = Opts::from_url(url).expect("Invalid database URL");

    // Establish a new connection
    let mut conn = Conn::connect(opts).expect("Failed to connect to MySQL");

    // SQL statement to insert a new row
    let stmt = "INSERT INTO todos (title, completed) VALUES (?, ?)";

    // Execute the prepared statement with the given parameters
    match conn.prep_exec(stmt, (name, age)) {
        Ok(result) => {
            // Log the number of affected rows
            println!("Row inserted: {}", result.affected_rows());
        }
        Err(e) => {
            // Log the error message
            eprintln!("Error: {}", e);
        }
    }
}