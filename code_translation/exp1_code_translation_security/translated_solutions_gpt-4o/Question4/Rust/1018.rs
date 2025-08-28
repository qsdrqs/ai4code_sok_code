use mysql::*;
use mysql::prelude::*;
use std::error::Error;

fn add(name: &str, age: i32, table: &str) -> Result<(), Box<dyn Error>> {
    // Load the configuration (assuming `config` is a module or struct that provides database configuration)
    let config = config::get_config(); // Replace with your actual config loading logic

    // Create a connection to the MySQL database
    let pool = Pool::new(config)?;
    let mut conn = pool.get_conn()?;

    // Prepare the SQL statement
    let stmt = format!("INSERT INTO {} (title, completed) VALUES (?, ?)", table);

    // Execute the query
    let result = conn.exec_drop(&stmt, (name, age))?;

    // Log the number of affected rows
    println!("Row inserted: {}", result);

    Ok(())
}

// Example configuration module (you can replace this with your actual implementation)
mod config {
    use mysql::OptsBuilder;

    pub fn get_config() -> OptsBuilder {
        let mut builder = OptsBuilder::new();
        builder.ip_or_hostname(Some("localhost"))
               .user(Some("your_username"))
               .pass(Some("your_password"))
               .db_name(Some("your_database"));
        builder
    }
}