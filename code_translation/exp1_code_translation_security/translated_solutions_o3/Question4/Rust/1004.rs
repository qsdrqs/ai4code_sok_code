// src/main.rs
// -------------
// The `mysql` crate (and any others that are needed) will be supplied for you
// in the project’s `Cargo.toml`, so we can just `use` them here.

use mysql::{prelude::Queryable, Pool};

/// add_student
///
/// Given a name and an age, build the corresponding INSERT statement,
/// print it to the console, and return it.
///
/// NOTE: just like the original JavaScript version, this is **not**
/// parameterised – it blindly interpolates the values into the SQL.
/// In real code you would use prepared statements / parameters.
fn add_student(name: &str, age: &str) -> String {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    println!("{sql}");
    sql
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // mysql://<user>:<password>@<host>:<port>/<db>
    // No DB name was supplied in the JS snippet, so we omit it here as well.
    let url = "mysql://yourusername:yourpassword@localhost:3306";

    // Establish the connection.
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    // Build the INSERT statement and execute it.
    let sql = add_student("XXXXX", "0");
    conn.query_drop(sql)?;   // `query_drop` executes a statement that returns no rows.

    println!("Result: OK");
    Ok(())
}