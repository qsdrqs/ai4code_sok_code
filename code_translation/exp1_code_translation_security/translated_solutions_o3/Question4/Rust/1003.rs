// lib.rs (or main.rs)
//
// Assumes you have `rusqlite = "*"` (or whatever version)
// declared in Cargo.toml.  All other crate setup is handled
// elsewhere, per the instructions.
//
// STUDENTS table layout: (NAME TEXT, AGE INTEGER)

use rusqlite::{params, Connection};

/// Insert a student (name, age) into the STUDENTS table.
///
/// Like the original JS it performs no validation on `name`/`age`;
/// it simply attempts the SQL and prints the resulting error (if
/// any) to stdout.
pub fn add_student(db: &Connection, name: &str, age: i32) {
    // `execute` returns Result<usize, rusqlite::Error>
    // (number of rows changed, or the error).
    let result = db.execute(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2)",
        params![name, age],
    );

    // Match the result so we can mimic the `console.log("inserted", err)`
    // semantics from JavaScript.
    match result {
        Ok(_) => println!("inserted {:?}", Option::<rusqlite::Error>::None),
        Err(e) => println!("inserted {:?}", Some(e)),
    }
}