use rusqlite::{Connection, Result};

fn add_new_entry(name: &str, age: i32) -> Result<()> {
    // Open the SQLite database
    let conn = Connection::open("database.db")?;

    // Insert a new student into the STUDENTS table
    conn.execute(
        "INSERT INTO STUDENTS VALUES (?, ?)",
        &[&name, &age],
    )?;

    // Prepare a SELECT statement to retrieve all students
    let mut stmt = conn.prepare("SELECT rowid AS id, NAME, AGE FROM STUDENTS")?;

    // Execute the query and iterate over the results
    let mut rows = stmt.query([])?;
    while let Some(row) = rows.next()? {
        let id: i64 = row.get("id")?;
        let name: String = row.get("NAME")?;
        let age: i32 = row.get("AGE")?;
        println!("{}: {} {}", id, name, age);
    }

    Ok(())
}

fn main() {
    // Call the function with the provided test data
    add_new_entry("XXXXX", 0).unwrap();
}