// In Rust, dependencies are imported using the `use` keyword.
// This is equivalent to `require('sqlite3')`.
use rusqlite::{Connection, Result, params};

// In Rust, it's idiomatic to define a struct to represent the data
// you are working with. This provides type safety.
// The `#[derive(Debug)]` allows us to easily print the struct for debugging.
#[derive(Debug)]
struct Student {
    // Note: We assume the table has an `id` column, which is standard practice.
    id: i32,
    name: String,
    age: i32,
}

/// Inserts a new student into the database.
/// This is the direct translation of the `student_write` function.
///
/// # Parameters
/// * `conn`: A borrowed reference to the database connection.
/// * `name`: The name of the student.
/// * `age`: The age of the student.
///
/// In Rust, functions that can fail should return a `Result` type.
/// `Result<()>` indicates it either succeeds (`Ok(())`) or returns an error.
fn student_write(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // `conn.execute` is used for statements that don't return rows (like INSERT, UPDATE, DELETE).
    // The `?` operator is used for error handling. If `execute` returns an error,
    // the function will immediately stop and return that error.
    // The `params!` macro provides a convenient way to pass parameters safely.
    conn.execute(
        "INSERT INTO STUDENTS (name, age) VALUES (?1, ?2)",
        params![name, age],
    )?;
    Ok(())
}

// The `main` function in Rust is the entry point of the program.
// Making it return a `Result` allows us to use the `?` operator for concise error handling.
fn main() -> Result<()> {
    // `Connection::open` is equivalent to `new sqlite3.Database()`.
    // Note: The path is relative to the execution directory, which is generally
    // better practice than a hardcoded absolute path.
    let conn = Connection::open("database.db")?;

    // For this example to be self-contained and runnable, we first ensure
    // the table exists. The original JS code assumes it's already there.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            id    INTEGER PRIMARY KEY,
            name  TEXT NOT NULL,
            age   INTEGER
        )",
        [], // No parameters for this query
    )?;

    // Call the function to write a student record.
    // This is equivalent to `student_write("XXXXX", 0)`.
    student_write(&conn, "XXXXX", 0)?;

    // The equivalent of `db.all("SELECT ...", callback)` is a bit different in Rust.
    // We first prepare a statement, which is more efficient and secure.
    let mut stmt = conn.prepare("SELECT id, name, age FROM STUDENTS")?;

    // Then, we use `query_map` to execute the query and map each row to our `Student` struct.
    // This creates an iterator, which is memory-efficient as it processes one row at a time
    // instead of loading all rows into memory at once like `db.all`.
    let student_iter = stmt.query_map([], |row| {
        // The closure `|row| { ... }` is executed for each row found.
        // `row.get(index)` retrieves a value from a column. It can also fail,
        // so we use `?` here as well.
        Ok(Student {
            id: row.get(0)?,
            name: row.get(1)?,
            age: row.get(2)?,
        })
    })?;

    // This is the equivalent of `rows.forEach(...)`.
    // We iterate over the results from our query.
    println!("Fetching all students from the database:");
    for student_result in student_iter {
        // `student_result` is a `Result<Student, Error>`. We unwrap it to get the Student.
        let student = student_result?;
        // The original JS code printed the row object twice. We replicate that here.
        println!("{:?}, {:?}", student, student);
    }

    // If all operations were successful, we return `Ok(())`.
    Ok(())
}