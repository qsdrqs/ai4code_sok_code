use mysql::{OptsBuilder, PooledConn, Pool};
use tokio;

// Define a struct to hold the database connection
struct Database {
    pool: Pool,
}

impl Database {
    // Create a new database connection
    async fn new(url: &str) -> Result<Self, mysql::Error> {
        let opts = OptsBuilder::new()
            .ip_or_hostname(Some(url))
            .db_name(Some("your_database_name"))
            .user(Some("your_username"))
            .pass(Some("your_password"));

        let pool = Pool::new(opts)?;
        Ok(Database { pool })
    }

    // Add a new student to the database
    async fn add_student(&self, name: &str, age: u8) -> Result<(), mysql::Error> {
        let conn: PooledConn = self.pool.get_conn().await?;
        let query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        conn.prep_exec(query, (name, age)).await?;
        Ok(())
    }
}

#[tokio::main]
async fn main() {
    // Replace with your actual database URL, username, password, and database name
    let db_url = "localhost";
    let db_username = "your_username";
    let db_password = "your_password";
    let db_name = "your_database_name";

    let db = Database::new(&format!("{}:{}", db_username, db_password)).await.unwrap();

    // Add a new student
    db.add_student("John Doe", 20).await.unwrap();
}