// main.rs

// ---- External crates ----
// (Add the following lines to Cargo.toml.  The assignment says the
// dependencies will be provided, but the names/versions are shown
// here for clarity)
//
// [dependencies]
// mysql = "24"        # or any recent 20-something version
// anyhow = "1"

// ---- Code ----
use mysql::{params, prelude::*, Pool};
use anyhow::Result;

/// Insert a single `(name, age)` tuple into the `STUDENTS` table.
///
/// `name` is stored in the `NAME` column, `age` in `AGE`.
///
/// Returns `Ok(())` on success or an error if anything goes wrong.
pub fn inserter(name: &str, age: u32) -> Result<()> {
    // Equivalent to the JS connection options:
    //   host:     "localhost"
    //   user:     "yourusername"
    //   password: "yourpassword"
    //   database: "mydb"
    //
    // Connection URL format:
    //   mysql://USER:PASSWORD@HOST[:PORT]/DATABASE
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";

    // Create a pool (cheap cloneable handle to a connection “pool”).
    let pool  = Pool::new(url)?;
    let mut conn = pool.get_conn()?;   // One real connection from the pool

    // Use a parametrised statement to avoid SQL-injection issues.
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

fn main() {
    // Example usage
    if let Err(e) = inserter("Alice", 19) {
        eprintln!("Insertion failed: {e}");
    }
}