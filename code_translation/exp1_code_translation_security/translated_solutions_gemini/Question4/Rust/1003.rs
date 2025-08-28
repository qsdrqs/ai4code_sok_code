use rusqlite::{params, Connection, Result};

// db: A reference to a rusqlite::Connection object, assumed to contain a STUDENTS (NAME, AGE) table.
// name: The student's name as a string slice.
// age: The student's age as a 32-bit integer.
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
pub fn add_student(db: &Connection, name: &str, age: i32) {
    // The original JS code uses a callback to log the result.
    // We can replicate this behavior with a `match` statement.
    // `db.execute` returns a Result: Ok(num_rows_changed) on success, or Err(e) on failure.
    match db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    ) {
        Ok(updated_rows) => {
            // The original JS logs `("inserted", null)`. A simple success message is a clearer Rust equivalent.
            println!(
                "Successfully inserted '{}'. Rows affected: {}",
                name, updated_rows
            );
        }
        Err(err) => {
            // The original JS logs `("inserted", err)`. We use `eprintln!` for errors.
            eprintln!("Error inserting student '{}': {}", name, err);
        }
    }
}

// A main function to demonstrate the usage of `add_student`.
fn main() -> Result<()> {
    // 1. Create an in-memory database for this example.
    let conn = Connection::open_in_memory()?;

    // 2. Create the STUDENTS table.
    conn.execute(
        "CREATE TABLE STUDENTS (
             id    INTEGER PRIMARY KEY,
             name  TEXT NOT NULL,
             age   INTEGER
         )",
        [], // No parameters for this query
    )?;

    // 3. Call our translated function to add some students.
    add_student(&conn, "Alice", 22);
    add_student(&conn, "Bob", 25);
    add_student(&conn, "Alice", 22); // This will also "succeed" as there's no UNIQUE constraint

    // 4. (Optional) Verify the data was inserted correctly.
    println!("\nVerifying contents of the database:");
    let mut stmt = conn.prepare("SELECT name, age FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok((row.get::<usize, String>(0)?, row.get::<usize, i32>(1)?))
    })?;

    for student in student_iter {
        let (name, age) = student?;
        println!("Found student: Name = {}, Age = {}", name, age);
    }

    Ok(())
}