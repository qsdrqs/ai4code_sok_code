use rusqlite::{Connection, Result, params};

/// Adds a new student entry to the `students` table.
///
/// # Arguments
///
/// * `conn` - A reference to an open SQLite connection.
/// * `name` - The name of the student.
/// * `age`  - The age of the student.
///
/// # Behavior
///
/// - Executes an `INSERT` statement to add a new student.
/// - Prints the ID of the newly inserted row if successful.
/// - Prints an error message if the insertion fails.
pub fn add_sql_entry(conn: &Connection, name: &str, age: i32) {
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    match conn.execute(sql, params![name, age]) {
        Ok(_) => {
            let last_id = conn.last_insert_rowid();
            println!("User added with id: {}", last_id);
        }
        Err(e) => {
            println!("Error: {}", e);
        }
    }
}