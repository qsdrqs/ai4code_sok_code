use mysql::{Opts, Pool};
use std::error::Error;

// Define a struct to hold the database configuration
struct Config {
    host: String,
    user: String,
    password: String,
    db_name: String,
}

fn add(name: &str, age: i32, table: &str) -> Result<(), Box<dyn Error>> {
    // Define the database configuration
    let config = Config {
        host: "localhost".to_string(),
        user: "your_username".to_string(),
        password: "your_password".to_string(),
        db_name: "your_database".to_string(),
    };

    // Create a MySQL connection pool
    let opts = Opts::new()
        .ip_or_hostname(Some(&config.host))
        .user(Some(&config.user))
        .pass(Some(&config.password))
        .db_name(Some(&config.db_name));

    let pool = Pool::new(opts)?;

    // Prepare the SQL query
    let query = format!("INSERT INTO {} (title, completed) VALUES (?)", table);

    // Execute the query
    let result = pool.prep_exec(query, (name, age))?;

    // Get the number of affected rows
    println!("Row inserted: {}", result.affected_rows());

    Ok(())
}

fn main() {
    // Example usage
    if let Err(err) = add("Example Todo", 30, "todos") {
        eprintln!("Error: {}", err);
    }
}