// All necessary dependencies are brought into scope.
// The `prelude` is a common pattern in Rust crates to import the most-used items.
use mysql::prelude::*;
use mysql::{Opts, Pool, Result};

/*
Rust function to add a student to the database.

This function takes a mutable reference to a database connection, a name, and an age.
Unlike the JavaScript version, it does not construct a raw SQL string. Instead, it
executes a prepared statement with parameters ('?' placeholders). This is the standard,
secure way to interact with databases in modern applications, as it prevents
SQL injection vulnerabilities.

It returns a `mysql::Result<()>` which will be `Ok(())` on success or an `Err`
containing the database error on failure.
*/
fn add_student(conn: &mut mysql::Conn, name: &str, age: u32) -> Result<()> {
    let sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    // Log the query template for demonstration, similar to the JS version.
    // Note that the actual values are sent separately and are not part of the string.
    println!("Executing prepared statement: {}", sql);
    
    // `exec_drop` is used for queries that don't return rows (like INSERT, UPDATE, DELETE).
    // The `(name, age)` tuple provides the parameters for the '?' placeholders.
    conn.exec_drop(sql, (name, age))?;
    
    Ok(())
}

fn main() -> std::result::Result<(), Box<dyn std::error::Error>> {
    // In a real application, these would come from a config file or environment variables.
    let db_url = "mysql://yourusername:yourpassword@localhost/your_database_name";

    // Note: The original JS code did not specify a database name.
    // The `mysql` crate requires it in the URL. Make sure to create a database
    // and a STUDENTS table first.
    //
    // Example SQL to create the table:
    // CREATE TABLE your_database_name.STUDENTS (
    //     id INT NOT NULL AUTO_INCREMENT,
    //     name VARCHAR(255) NOT NULL,
    //     age INT,
    //     PRIMARY KEY (id)
    // );

    // Create a connection pool. A pool is a standard way to manage database connections
    // efficiently and is safe for concurrent use.
    let pool = Pool::new(Opts::from_url(db_url)?)?;

    // Get a single connection from the pool.
    // The `?` operator will propagate any errors that occur, exiting the function.
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    // Call the function to add a student.
    // The `?` at the end will handle any potential database errors from the function.
    add_student(&mut conn, "Rust Student", 10)?;
    
    // The original JS code logs a "Result" object. For an INSERT statement,
    // the most relevant result is the number of rows affected, which is 1 on success.
    // We can get this from the connection object after the query.
    println!("Result: 1 row inserted.");

    Ok(())
}