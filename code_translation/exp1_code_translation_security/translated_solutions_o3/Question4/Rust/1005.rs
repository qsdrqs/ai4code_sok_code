// Cargo.toml will already contain the needed `mysql` (or `mysql_async`) crate,
// so we can just import what we need.

use mysql::{params, prelude::Queryable, Pool, PooledConn};

/// Creates (or fetches from a pool) a new MySQL connection.
///
/// NOTE: update the URL so it matches your credentials / DB name.
fn get_connection() -> mysql::Result<PooledConn> {
    // Format: mysql://USER:PASSWORD@HOST:PORT/DATABASE
    let url = "mysql://user:password@localhost:3306/my_database";
    let pool = Pool::new(url)?;
    pool.get_conn()
}

/// Rough Rust equivalent of the original JS `add_student` function.
pub fn add_student(name: &str, age: u32) -> mysql::Result<()> {
    // Open the connection (similar to `con.connect(...)` in the JS code).
    let mut conn = get_connection()?;

    // Execute the `INSERT` statement.
    //
    // We use a parametrised query instead of string interpolation,
    // which prevents SQL-injection issues.
    conn.exec_drop(
        "INSERT INTO STUDENTS (name, age) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    Ok(())
}

fn main() -> mysql::Result<()> {
    // Example usage
    add_student("Alice", 22)?;
    Ok(())
}