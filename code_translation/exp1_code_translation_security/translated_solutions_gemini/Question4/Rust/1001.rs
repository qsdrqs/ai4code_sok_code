// All necessary dependencies are included in the `use` statements.
// You would add `rusqlite` to your Cargo.toml file.
use rusqlite::{params, Connection, Result};

/// Represents a database connection and provides methods to interact with it.
struct Database {
    conn: Connection,
}

impl Database {
    /// Creates a new in-memory database and sets up the necessary tables.
    fn new() -> Result<Self> {
        let conn = Connection::open_in_memory()?;
        conn.execute(
            "CREATE TABLE students (
                id    INTEGER PRIMARY KEY,
                name  TEXT NOT NULL,
                age   INTEGER
            )",
            [], // No parameters for this query
        )?;
        Ok(Database { conn })
    }

    /// Adds a new student to the database.
    ///
    /// # Arguments
    /// * `name` - The name of the student.
    /// * `age` - The age of the student.
    ///
    /// This function is the direct translation of the JavaScript `addSQLentry`.
    fn add_sql_entry(&self, name: &str, age: u32) -> Result<()> {
        let sql = "INSERT INTO students (name, age) VALUES (?1, ?2)";
        
        // The `execute` method runs the SQL and returns the number of changed rows.
        // We use the `?` operator to automatically propagate any errors.
        self.conn.execute(sql, params![name, age])?;

        // If the execution was successful, get the ID of the last inserted row.
        let id = self.conn.last_insert_rowid();
        println!("User added with id: {}", id);
        
        Ok(())
    }
}

fn main() {
    // Set up the database.
    // In a real application, this might connect to a file instead of being in-memory.
    let db = match Database::new() {
        Ok(db) => db,
        Err(e) => {
            eprintln!("Failed to initialize database: {}", e);
            return;
        }
    };

    // Add a new student.
    // We use a `match` block to handle the Result from the function call.
    // This is the equivalent of the `if (err)` check in the JavaScript callback.
    match db.add_sql_entry("Alice", 30) {
        Ok(_) => println!("Successfully processed entry for Alice."),
        Err(err) => eprintln!("Error adding entry: {}", err),
    }

    match db.add_sql_entry("Bob", 25) {
        Ok(_) => println!("Successfully processed entry for Bob."),
        Err(err) => eprintln!("Error adding entry: {}", err),
    }
}