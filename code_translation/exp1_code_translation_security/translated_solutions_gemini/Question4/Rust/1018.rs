// src/main.rs

// Import necessary items from the mysql crate and standard library
use mysql::{Pool, OptsBuilder, prelude::Queryable};
use std::error::Error;

// Import our custom config module
mod config;
use config::Config;

/// Inserts a new record into a specified table.
///
/// # Arguments
/// * `name` - The value for the 'title' column.
/// * `age` - The value for the 'completed' column.
/// * `table` - The name of the table to insert into.
///
/// # Returns
/// A Result indicating success (Ok) or the specific database error (Err).
fn add(name: &str, age: u32, table: &str) -> Result<(), Box<dyn Error>> {
    // 1. Load configuration
    // This replaces `let config = require('./config.js');`
    let app_config = Config::from_env()?;
    let db_config = app_config.db;

    // 2. Create a connection URL and a connection pool
    // This replaces `mysql.createConnection(config);`
    let opts = OptsBuilder::new()
        .user(Some(db_config.user))
        .pass(Some(db_config.pass))
        .ip_addr(Some(db_config.host))
        .tcp_port(db_config.port)
        .db_name(Some(db_config.name));

    let pool = Pool::new(opts)?;
    let mut conn = pool.get_conn()?;

    // 3. Prepare the data and the SQL statement
    // This replaces `let todos = [name, age];` and the `stmt` string.
    // Note: The original JS code has a potential mismatch where 'age' is inserted
    // into a 'completed' column. We replicate that logic here.
    // Using `format!` for the table name is safe if the input is controlled.
    // Using `?` placeholders for values prevents SQL injection.
    let stmt = format!("INSERT INTO {} (title, completed) VALUES (?, ?)", table);

    // 4. Execute the query
    // This replaces `connection.query(...)` and its callback.
    // `exec_drop` is used for statements that don't return rows (INSERT, UPDATE, DELETE).
    conn.exec_drop(&stmt, (name, age))?;

    // 5. Get and print the number of affected rows
    // This replaces `console.log('Row inserted:' + results.affectedRows);`
    println!("Row inserted: {}", conn.affected_rows());

    Ok(())
}

// A main function to demonstrate how to call `add`.
fn main() {
    // Example call to the add function.
    // In a real application, these values might come from user input or an API request.
    if let Err(err) = add("Learn Rust", 30, "todos") {
        // This replaces `console.error(err.message);`
        eprintln!("Error adding record: {}", err);
    }
}