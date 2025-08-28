use rusqlite::{Connection, Result, params};
use std::sync::{Arc, Mutex};

// Database connection wrapper
pub struct Database {
    conn: Arc<Mutex<Connection>>,
}

impl Database {
    // Initialize database connection
    pub fn new(db_path: &str) -> Result<Self> {
        let conn = Connection::open(db_path)?;
        Ok(Database {
            conn: Arc::new(Mutex::new(conn)),
        })
    }

    // Rust function to create SQL table "students" with 'name' and 'age'
    pub fn create_table(&self) -> Result<()> {
        let conn = self.conn.lock().unwrap();
        
        match conn.execute(
            "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)",
            [],
        ) {
            Ok(_) => {
                println!("Table created successfully");
                Ok(())
            }
            Err(e) => {
                println!("Error occurred while creating the table: {}", e);
                Err(e)
            }
        }
    }

    // Rust function to insert string name and an int age to 'students'
    pub fn insert_student(&self, name: &str, age: i32) -> Result<()> {
        let conn = self.conn.lock().unwrap();
        
        match conn.execute(
            "INSERT INTO students (name, age) VALUES (?, ?)",
            params![name, age],
        ) {
            Ok(_) => {
                println!("Student inserted successfully");
                Ok(())
            }
            Err(e) => {
                println!("Error occurred while inserting student: {}", e);
                Err(e)
            }
        }
    }
}

// Example usage
fn main() -> Result<()> {
    // Create database connection (equivalent to openDatabase)
    let db = Database::new("mydb.sqlite")?;
    
    // Create table
    db.create_table()?;
    
    // Insert a student
    db.insert_student("John Doe", 20)?;
    
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::*;
    use tempfile::NamedTempFile;

    #[test]
    fn test_create_table_and_insert() -> Result<()> {
        let temp_file = NamedTempFile::new().unwrap();
        let db = Database::new(temp_file.path().to_str().unwrap())?;
        
        // Test table creation
        assert!(db.create_table().is_ok());
        
        // Test student insertion
        assert!(db.insert_student("Alice", 22).is_ok());
        assert!(db.insert_student("Bob", 25).is_ok());
        
        Ok(())
    }
}