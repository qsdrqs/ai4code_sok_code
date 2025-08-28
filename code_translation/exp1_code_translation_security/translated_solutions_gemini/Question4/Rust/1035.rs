use rusqlite::{params, Connection, Result};

// In Rust, it's common to define a struct to represent the data
// you are working with. The `Debug` trait allows us to print it easily.
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

/// Inserts a new student into the database.
///
/// # Arguments
/// * `conn` - A reference to the active SQLite connection.
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// Note: In the JS version, `db.serialize` was used to ensure sequential execution.
/// In `rusqlite`, operations on a single `Connection` are already serialized,
/// so no special wrapper is needed.
fn insert(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // The JS code prepares, runs, and finalizes the statement inside the function.
    // In Rust, preparing a statement is also a best practice.
    let mut stmt = conn.prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")?;

    // The `params!` macro is a convenient way to provide parameters.
    stmt.execute(params![name, age])?;

    // In Rust, `stmt` is automatically finalized when it goes out of scope (RAII).
    // There is no need for an explicit `stmt.finalize()` call.
    Ok(())
}

fn main() -> Result<()> {
    // `Connection::open` is equivalent to `new sqlite3.Database()`.
    // The `?` operator is used for error handling. If `open` fails,
    // the program will exit and print the error.
    let conn = Connection::open("studentdb.db")?;

    // `conn.execute` is equivalent to `db.run`. It's used for statements
    // that don't return rows.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT NOT NULL,
            AGE  INTEGER
        )",
        [], // empty list of parameters.
    )?;

    // Call the insert function for each student.
    insert(&conn, "Foo", 42)?;
    insert(&conn, "Bar", 55)?;

    println!("Successfully inserted students.");

    // This section demonstrates how to achieve what the commented-out
    // `db.each` block in the JavaScript code would have done.
    println!("\nRetrieving all students from the database:");
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;

    // `query_map` executes the query and maps each row to a Rust struct.
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?, // Get value from the first column (index 0)
            age: row.get(1)?,  // Get value from the second column (index 1)
        })
    })?;

    for student in student_iter {
        // `student?` will unwrap the Result, propagating any error.
        println!("{:?}", student?);
    }

    // The database connection `conn` is automatically closed when `main` finishes.
    // This is another example of RAII, so `db.close()` is not needed.
    Ok(())
}