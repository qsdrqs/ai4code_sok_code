//! Inserts a new row into the SQL table `STUDENTS`.
//
// Assumptions
// -----------
// 1. A MySQL / MariaDB database is used (similar to the `mysql` package in Node).
// 2. A `mysql::Pool` has already been created elsewhere and a `PooledConn`
//    (database connection) is handed to this function.
// 3. Error handling is propagated with `Result` instead of `throw`.
//
// Required dependency in Cargo.toml (already taken care of for you):
// mysql = "23"   // or any compatible version

use mysql::{prelude::Queryable, PooledConn};

/// Adds a student to the STUDENTS table.
///
/// # Arguments
/// * `conn` – An open database connection.
/// * `name` – The student's name.
/// * `age`  – The student's age.
///
/// # Errors
/// Any database error is returned as `mysql::Error`.
pub fn add(conn: &mut PooledConn, name: &str, age: u32) -> Result<(), mysql::Error> {
    // Build the statement exactly like the JavaScript version.
    // (In production you’d use a prepared statement to avoid SQL injection.)
    let sql = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );
    println!("{}", &sql);

    // Execute the query.
    conn.query_drop(sql)?;

    println!("1 record inserted");
    Ok(())
}