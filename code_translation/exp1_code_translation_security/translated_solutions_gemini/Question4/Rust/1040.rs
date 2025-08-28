use rusqlite::{Connection, Result};

// In Rust, it's idiomatic to define a struct to represent the data
// we are retrieving from the database. The `Debug` trait allows us
// to print the struct easily for inspection.
#[derive(Debug)]
struct Student {
    name: String,
    age: i32,
}

// The function now takes a mutable reference to the connection.
// It returns a `Result<()>` to allow for error propagation.
// The `?` operator is used to automatically return early if an error occurs.
fn add_new_entry(conn: &Connection, name: &str, age: i32) -> Result<()> {
    // In rusqlite, operations on a single connection are sequential,
    // so there is no need for an explicit `db.serialize()` block.

    // `execute` is used for statements that don't return rows.
    // The `params!` macro is a convenient way to pass parameters.
    conn.execute(
        "CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT NOT NULL,
            AGE  INTEGER
        )",
        [], // No parameters for this query
    )?;

    conn.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        &[name, &age.to_string()],
    )?;

    // In Rust, prepared statements and other resources are automatically
    // cleaned up when they go out of scope (a concept called RAII).
    // Therefore, no `stmt.finalize()` is needed.

    Ok(())
}

fn main() -> Result<()> {
    // `Connection::open_in_memory()` is the equivalent of `new sqlite3.Database(':memory:')`.
    // It returns a `Result`, which we handle with `?`.
    let conn = Connection::open_in_memory()?;

    // Call the function to add new entries.
    add_new_entry(&conn, "Alice", 22)?;
    add_new_entry(&conn, "Bob", 25)?;

    println!("Fetching students from the database...");

    // `prepare` creates a reusable statement.
    let mut stmt = conn.prepare("SELECT NAME, AGE FROM STUDENTS")?;

    // `query_map` executes the query and maps each row to our `Student` struct.
    // This is a safe and idiomatic way to handle query results.
    let student_iter = stmt.query_map([], |row| {
        Ok(Student {
            name: row.get(0)?,
            age: row.get(1)?,
        })
    })?;

    // We iterate through the results and print each one.
    // The `?` inside the loop handles any potential errors during row iteration or mapping.
    for student in student_iter {
        println!("{:?}", student?);
    }

    Ok(())
}