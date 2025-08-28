use rusqlite::{params, Connection, Result};

// There is a SQL table named “STUDENTS” that contains columns for “NAME” and “AGE”.

/// A struct to hold student data, which is a good practice in Rust for type safety.
#[derive(Debug)]
struct Student {
    name: String,
    age: u32,
}

/// Adds a new student to the "STUDENTS" table.
///
/// # Arguments
/// * `conn` - A reference to the active SQLite connection.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// A `Result` containing the number of rows affected (should be 1 on success)
/// or an error if the insertion fails.
fn add_student(conn: &Connection, name: &str, age: u32) -> Result<usize> {
    // Use a parameterized query `?1, ?2` to prevent SQL injection.
    // `rusqlite` automatically maps the types from the `params!` macro.
    let rows_affected = conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    )?;

    // The `execute` method returns the number of rows that were changed.
    Ok(rows_affected)
}

fn main() -> Result<()> {
    // Test example

    // 1. Create/open the SQLite database file.
    let conn = Connection::open("database.db")?;

    // 2. Create the table if it doesn't exist.
    // Using `CREATE TABLE IF NOT EXISTS` is idempotent and safe to run multiple times.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            id    INTEGER PRIMARY KEY,
            name  TEXT NOT NULL,
            age   INTEGER
        )",
        [], // No parameters needed for this query
    )?;

    // 3. Add a new student using our function.
    let name_to_add = "Alice";
    let age_to_add = 30;
    match add_student(&conn, name_to_add, age_to_add) {
        Ok(rows) => println!("Successfully inserted {} row for {}.", rows, name_to_add),
        Err(e) => eprintln!("Error adding student: {}", e),
    }
    
    // Add another student for demonstration
    add_student(&conn, "Bob", 25)?;


    // 4. Display the contents of the database.
    println!("\nCurrent students in the database:");
    let mut stmt = conn.prepare("SELECT name, age FROM STUDENTS")?;
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age: row.get(1)?,
        })
    })?;

    for student in student_iter {
        // The `student` variable here is a `Result<Student, rusqlite::Error>`
        // `?` could be used, but a match or `if let` is safer inside a loop.
        match student {
            Ok(s) => println!(" - Name: {}, Age: {}", s.name, s.age),
            Err(e) => eprintln!("Error reading row: {}", e),
        }
    }

    // 5. The database connection is automatically closed when `conn` goes out of scope.
    // This is due to a concept in Rust called RAII (Resource Acquisition Is Initialization).
    // No explicit `db.close()` is needed.

    Ok(())
}