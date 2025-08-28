use rusqlite::{params, Connection, Result};

// This function adds a student to the STUDENTS table in the SQLite database.
// It doesn't perform any error checking on the types or sizes of `name` and `age`.
// If you care about validation, ensure the inputs are sanitized before calling this function.
pub fn add_student(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // Execute the SQL statement to insert the student into the database
    match conn.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)", params![name, age]) {
        Ok(_) => {
            println!("Inserted successfully");
            Ok(())
        }
        Err(err) => {
            println!("Error inserting: {}", err);
            Err(err)
        }
    }
}