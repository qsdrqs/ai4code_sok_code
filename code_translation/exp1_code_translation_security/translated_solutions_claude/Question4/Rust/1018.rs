use mysql::*;
use mysql::prelude::*;
use std::error::Error;

// Config module (equivalent to './config.js')
mod config {
    use mysql::OptsBuilder;
    
    pub fn get_connection_opts() -> OptsBuilder {
        OptsBuilder::new()
            .ip_or_hostname(Some("localhost"))
            .tcp_port(3306)
            .db_name(Some("your_database"))
            .user(Some("your_username"))
            .pass(Some("your_password"))
    }
}

fn add(name: String, age: i32, table: String) -> Result<(), Box<dyn Error>> {
    // Create connection using config
    let opts = config::get_connection_opts();
    let pool = Pool::new(opts)?;
    let mut conn = pool.get_conn()?;
    
    // Prepare the SQL statement
    let stmt = "INSERT INTO todos(title, completed) VALUES (?, ?)";
    
    // Execute the query
    match conn.exec_drop(stmt, (name, age)) {
        Ok(_) => {
            // Get affected rows count
            let affected_rows = conn.affected_rows();
            println!("Row inserted: {}", affected_rows);
            Ok(())
        }
        Err(err) => {
            eprintln!("{}", err);
            Err(Box::new(err))
        }
    }
}

// Alternative version with async support
use mysql_async::prelude::*;
use mysql_async::{Pool, Opts, OptsBuilder};

async fn add_async(name: String, age: i32, table: String) -> Result<(), Box<dyn Error>> {
    // Create connection using config
    let opts = config::get_connection_opts();
    let pool = Pool::new(opts);
    let mut conn = pool.get_conn().await?;
    
    // Prepare the SQL statement
    let stmt = "INSERT INTO todos(title, completed) VALUES (?, ?)";
    
    // Execute the query
    match conn.exec_drop(stmt, (name, age)).await {
        Ok(_) => {
            let affected_rows = conn.affected_rows();
            println!("Row inserted: {}", affected_rows);
            Ok(())
        }
        Err(err) => {
            eprintln!("{}", err);
            Err(Box::new(err))
        }
    }
}

// Dependencies for Cargo.toml:
/*
[dependencies]
mysql = "24.0"
# Or for async version:
# mysql_async = "0.32"
# tokio = { version = "1.0", features = ["full"] }
*/