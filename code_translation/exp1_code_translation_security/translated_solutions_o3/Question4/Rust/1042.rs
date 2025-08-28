use mysql::{params, PooledConn, prelude::*};

/// Inserts a single row in the STUDENTS table.
///
/// # Arguments
/// * `conn` – An open MySQL connection obtained from a `Pool`.
/// * `name` – The student’s name.
/// * `age` – The student’s age.
///
/// # Errors
/// Forwards any error coming from the database driver.
pub fn add_student(conn: &mut PooledConn, name: &str, age: u32) -> Result<(), mysql::Error> {
    // Using a parameterized statement to avoid SQL-injection issues.
    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    println!("1 record inserted");
    Ok(())
}