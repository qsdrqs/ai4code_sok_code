use mysql::{Opts, PooledConn, Pool};
use std::error::Error;

// Define a struct to hold database connection options
struct DbConfig {
    host: String,
    user: String,
    password: String,
    db_name: String,
}

impl DbConfig {
    fn new(host: String, user: String, password: String, db_name: String) -> Self {
        DbConfig {
            host,
            user,
            password,
            db_name,
        }
    }

    fn create_pool(&self) -> Result<Pool, Box<dyn Error>> {
        let opts = Opts {
            ip_or_hostname: Some(self.host.clone()),
            user: Some(self.user.clone()),
            pass: Some(self.password.clone()),
            db_name: Some(self.db_name.clone()),
            ..Default::default()
        };

        Pool::new(opts)
    }
}

// Function to add a new student
fn add_student(pool: &Pool, name: &str, age: u8) -> Result<(), Box<dyn Error>> {
    let mut conn: PooledConn = pool.get_conn()?;
    let query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    conn.prep_exec(query, (name, age))?;
    Ok(())
}

fn main() -> Result<(), Box<dyn Error>> {
    // Define database connection options
    let db_config = DbConfig::new(
        "localhost".to_string(),
        "your_username".to_string(),
        "your_password".to_string(),
        "your_database".to_string(),
    );

    // Create a database connection pool
    let pool = db_config.create_pool()?;

    // Add a new student
    add_student(&pool, "John Doe", 20)?;

    Ok(())
}