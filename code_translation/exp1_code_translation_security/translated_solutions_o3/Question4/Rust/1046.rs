// main.rs

use mysql::*;
use mysql::prelude::*;

/// Inserts a student into the `STUDENTS` table.
///
/// `name` – Student’s name  
/// `age`  – Student’s age
pub fn insert_into(name: &str, age: u32) -> Result<()> {
    // Replace this with whatever mechanism you already use to build the URL:
    // e.g. "mysql://user:password@localhost:3306/database"
    let url = std::env::var("DATABASE_URL")
        .expect("set DATABASE_URL or hard-code the connection string here");

    // Create a connection pool and pull out one connection.
    // The pool/connection is closed automatically when it goes out of scope.
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    // Use a prepared statement to avoid SQL-injection.
    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    Ok(())
}

fn main() -> Result<()> {
    insert_into("Alice", 21)?;
    println!("Student inserted.");
    Ok(())
}