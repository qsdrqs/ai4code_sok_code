use mysql::{params, prelude::Queryable, Pool};
use std::error::Error;

/// Inserts a student row into the STUDENTS table.
///
/// `name` – Student’s name  
/// `age`  – Student’s age
pub fn insertion(name: &str, age: u32) -> Result<(), Box<dyn Error>> {
    // The connection URL will be supplied for you in Cargo.toml / env vars.
    // Example format: mysql://user:password@host:port/database
    let url = std::env::var("DATABASE_URL")?;
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;

    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    Ok(())
}

fn main() -> Result<(), Box<dyn Error>> {
    insertion("Alice", 21)?;
    Ok(())
}