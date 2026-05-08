// Cargo.toml dependencies:
// [dependencies]
// mysql = "25"

use mysql::prelude::*;
use mysql::*;

// config.rs module (equivalent to ./config.js)
mod config {
    pub fn get_config() -> String {
        // Return your MySQL connection URL
        String::from("mysql://user:password@localhost:3306/database")
    }
}

fn add(name: &str, age: &str, _table: &str) {
    let url = config::get_config();

    // Create a connection pool (equivalent to mysql.createConnection)
    let pool = match Pool::new(url.as_str()) {
        Ok(p) => p,
        Err(e) => {
            eprintln!("{}", e);
            return;
        }
    };

    let mut connection = match pool.get_conn() {
        Ok(c) => c,
        Err(e) => {
            eprintln!("{}", e);
            return;
        }
    };

    let todos = vec![(name, age)];
    let stmt = "INSERT INTO todos(title, completed) VALUES (?, ?)";

    // Equivalent to connection.query with callback
    match connection.exec_batch(stmt, todos) {
        Ok(_) => {
            println!("Row inserted: {}", connection.affected_rows());
        }
        Err(e) => {
            eprintln!("{}", e);
        }
    }
}