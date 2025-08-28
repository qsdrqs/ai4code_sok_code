use rusqlite::{Connection, Result};

fn main() -> Result<()> {
    // Establish a connection to the database
    let conn = Connection::open("studentdb")?;

    // Create table if it doesn't exist
    conn.execute_batch(
        "BEGIN;
            CREATE TABLE IF NOT EXISTS STUDENTS (
                NAME TEXT,
                AGE INT
            );
        COMMIT;",
    )?;

    // Function to insert a student into the database
    let insert = |name: &str, age: i32| -> Result<()> {
        conn.execute(
            "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
            &[&name, &age],
        )?;
        Ok(())
    };

    // Insert students
    insert("Foo", 42)?;
    insert("Bar", 55)?;

    // Fetch and print all students
    let mut stmt = conn.prepare("SELECT * FROM STUDENTS")?;
    let mut rows = stmt.query_map([], |row| {
        let name: String = row.get(0)?;
        let age: i32 = row.get(1)?;
        Ok((name, age))
    })?;

    while let Some(row) = rows.next()? {
        let (name, age) = row?;
        println!("Name: {}, Age: {}", name, age);
    }

    // Close the database connection
    Ok(())
}