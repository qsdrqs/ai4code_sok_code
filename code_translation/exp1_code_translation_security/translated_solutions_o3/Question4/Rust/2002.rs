// The necessary crates (e.g. `tokio`, `mysql_async`, etc.) are assumed to be
// available in your Cargo.toml; only the function itself is shown here.

use mysql_async::{prelude::Queryable, Conn, Error};

/// Insert a new student row into the STUDENTS table.
///
/// * `conn` – an open MySQL connection (mutable so we can issue a query)
/// * `name` – the student's name
/// * `age`  – the student's age
///
/// Returns `Ok(())` if the INSERT succeeds or a `mysql_async::Error` otherwise.
pub async fn add_student(
    conn: &mut Conn,
    name: &str,
    age: u32,
) -> Result<(), Error> {
    // The SQL prepared statement (place-holders match the JS version: `?, ?`)
    let query = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

    // Execute the INSERT with the supplied parameters.  `exec_drop` is used
    // because we do not need the result set—just whether the statement
    // succeeded or failed.
    conn.exec_drop(query, (name, age)).await
}