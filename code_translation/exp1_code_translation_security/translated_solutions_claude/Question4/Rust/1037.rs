use mysql::*;
use mysql::prelude::*;

// Configuration structure (you'll need to define this with your actual database config)
struct Config {
    host: String,
    user: String,
    password: String,
    database: String,
}

fn insert_student(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    // Create database URL from config
    let config = Config {
        host: "localhost".to_string(),
        user: "your_username".to_string(),
        password: "your_password".to_string(),
        database: "your_database".to_string(),
    };
    
    let url = format!(
        "mysql://{}:{}@{}/{}",
        config.user, config.password, config.host, config.database
    );
    
    // Create connection
    let pool = Pool::new(url.as_str())?;
    let mut conn = pool.get_conn()?;
    
    // Insert statement
    let sql = "INSERT INTO STUDENTS(title,completed) VALUES('Learn how to insert a new row',true)";
    
    // Execute the insert statement
    conn.query_drop(sql)?;
    
    // Connection is automatically closed when it goes out of scope
    Ok(())
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    insert_student("XXXXX", 0)?;
    Ok(())
}