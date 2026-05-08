use rusqlite::{Connection, Result};

// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// Define a Rust function that takes as input a String name and an int age and adds a new entry to "STUDENTS".
// The function should return the number of rows affected.

fn add_student(db: &Connection, name: &str, age: i32) -> Result<usize> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    let result = db.execute(&sql, [])?;

    // Print result
    println!("{}", result);

    Ok(result)
}

// Test example
/*
fn main() -> Result<()> {
    // Create sql database
    let db = Connection::open("./database.db")?;

    // Create table (only do this once!)
    // let sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
    // db.execute(sql, [])?;

    add_student(&db, "XXXXX", 0)?;

    // Display contents of database
    let mut stmt = db.prepare("SELECT NAME, AGE FROM STUDENTS")?;
    let rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;
        Ok((name, age))
    })?;

    for row in rows {
        let (name, age) = row?;
        println!("{} {}", name, age);
    }

    // Close database - Rust handles this automatically when Connection is dropped
    Ok(())
}
*/