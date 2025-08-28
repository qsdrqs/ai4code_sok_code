use mysql::{params, Pool, PooledConn, prelude::*};

/// Insert one row into the STUDENTS table.
///
/// # Arguments
/// * `name` – student’s name
/// * `age`  – student’s age
///
/// # Errors
/// If the database can’t be reached or the statement fails,
/// the error is bubbled up to the caller.
pub fn add_student(name: &str, age: u32) -> Result<(), Box<dyn std::error::Error>> {
    // mysql://user:password@host:port/database
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";

    // Create a connection pool and fetch a single connection.
    let pool: Pool = Pool::new(url)?;
    let mut conn: PooledConn = pool.get_conn()?;

    // Execute the INSERT (using a prepared statement to stay safe).
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

// Example usage
fn main() {
    if let Err(err) = add_student("Alice", 19) {
        eprintln!("Database error: {err}");
    }
}