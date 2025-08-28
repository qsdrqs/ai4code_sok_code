use mysql::*;
use mysql::prelude::*;

// Assuming you have a config structure like this:
struct Config {
    host: String,
    user: String,
    password: String,
    database: String,
}

fn insert_into(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    // Create connection using config (you would initialize this with your actual config)
    let config = Config {
        host: "localhost".to_string(),
        user: "your_user".to_string(),
        password: "your_password".to_string(),
        database: "your_database".to_string(),
    };
    
    let url = format!("mysql://{}:{}@{}/{}", 
                     config.user, config.password, config.host, config.database);
    
    let pool = Pool::new(url.as_str())?;
    let mut conn = pool.get_conn()?;
    
    // Using parameterized query to prevent SQL injection
    let sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
    
    conn.exec_drop(sql, (name, age))?;
    
    // Connection is automatically closed when it goes out of scope
    Ok(())
}

// Alternative version with direct connection string
fn insert_into_alt(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let url = "mysql://user:password@localhost/database_name";
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    
    conn.exec_drop(
        "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)",
        (name, age)
    )?;
    
    Ok(())
}