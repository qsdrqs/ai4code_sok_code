use rusqlite::{Connection, Result};

// Define a Rust function that takes as input a String name and an i32 age and adds a new entry to "STUDENTS".
// The function should return the number of rows affected.
fn add_student(db: &Connection, name: &str, age: i32) -> Result<usize> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    let result = db.execute(&sql, [])?;
    
    // Print result
    println!("{}", result);
    
    Ok(result)
}

fn main() -> Result<()> {
    // Test example
    
    // Create sql database
    let db = Connection::open("./database.db")?;
    
    // Create table (only do this once!)
    // let sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INTEGER)";
    // db.execute(sql, [])?;
    
    add_student(&db, "XXXXX", 0)?;
    
    // Display contents of database
    let mut stmt = db.prepare("SELECT * FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((
            row.get::<_, String>(0)?, // NAME
            row.get::<_, i32>(1)?     // AGE
        ))
    })?;
    
    for student in student_iter {
        let (name, age) = student?;
        println!("{} {}", name, age);
    }
    
    // Database connection is automatically closed when it goes out of scope
    
    Ok(())
}