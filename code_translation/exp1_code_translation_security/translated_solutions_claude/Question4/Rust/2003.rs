use rusqlite::{Connection, Result, params};
use std::collections::HashMap;

fn main() -> Result<()> {
    // Create an in-memory database
    let mut db = Connection::open_in_memory()?;
    
    // Create the students table
    db.execute(
        "CREATE TABLE students (name TEXT, age INT)",
        [],
    )?;
    
    // Insert a student
    insert_student(&mut db, "XXXXX", 0)?;
    
    // Query and print all students
    let mut stmt = db.prepare("SELECT * FROM students")?;
    let student_iter = stmt.query_map([], |row| {
        let mut result = HashMap::new();
        result.insert("name".to_string(), row.get::<_, String>(0)?);
        result.insert("age".to_string(), row.get::<_, i32>(1)?.to_string());
        Ok(result)
    })?;
    
    for student in student_iter {
        match student {
            Ok(row) => println!("{:?}", row),
            Err(e) => eprintln!("Error reading row: {}", e),
        }
    }
    
    // Database connection is automatically closed when it goes out of scope
    Ok(())
}

fn insert_student(db: &mut Connection, name: &str, age: i32) -> Result<()> {
    db.execute(
        "INSERT INTO students (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}