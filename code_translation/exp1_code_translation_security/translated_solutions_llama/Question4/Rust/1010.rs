use rusqlite::{Connection, Result};

// Define a Rust function that takes as input a String name and an i32 age and adds a new entry to "STUDENTS".
// The function should return the number of rows affected.
fn add_student(conn: &Connection, name: &str, age: i32) -> Result<i32> {
    let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)")?;
    let result = stmt.execute([name, &age.to_string()])?;
    Ok(result)
}

fn main() -> Result<()> {
    // Create sql database
    let conn = Connection::open("./database.db")?;

    // Create table (only do this once!)
    // let sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
    // conn.execute(sql, ())?;

    // Test example
    add_student(&conn, "XXXXX", 0)?;

    // Display contents of database
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let mut rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;
        Ok((name, age))
    })?;

    while let Some(row) = rows.next()? {
        let (name, age) = row?;
        println!("{} {}", name, age);
    }

    // Close database (automatic with RAII)
    Ok(())
}