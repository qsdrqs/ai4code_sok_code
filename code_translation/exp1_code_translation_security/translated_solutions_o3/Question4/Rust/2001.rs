use mysql::{params, PooledConn, prelude::*};

/// Inserts a student into the `students` table.
///
/// * `conn` – an open MySQL connection (from e.g. a `mysql::Pool`).
/// * `name` – the student’s name.
/// * `age`  – the student’s age.
///
/// On success the function prints a confirmation message; on failure the error
/// is bubbled up to the caller.
pub fn add(conn: &mut PooledConn, name: &str, age: u32) -> Result<(), mysql::Error> {
    // Equivalent to: INSERT INTO students (name, age) VALUES (?, ?)
    conn.exec_drop(
        "INSERT INTO students (name, age) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    println!("Added {} to students table", name);
    Ok(())
}