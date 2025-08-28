use mysql::{prelude::*, Pool};
use std::io::{self, Write};

fn main() -> mysql::Result<()> {
    // ---------------------------------------------------------------------
    // 1. Build a connection URL of the form:
    //    mysql://<user>:<password>@<host>:<port>/<database>
    // ---------------------------------------------------------------------
    let url = "mysql://yourusername:yourpassword@localhost:3306/mydb";

    // ---------------------------------------------------------------------
    // 2. Establish a synchronous (blocking) connection
    // ---------------------------------------------------------------------
    let pool = Pool::new(url)?;          // create a pool
    let mut conn = pool.get_conn()?;     // grab a single connection

    println!("Connected!");

    // ---------------------------------------------------------------------
    // 3. Read user input
    // ---------------------------------------------------------------------
    let name = read_line("enter a name: ");
    let age: i32 = read_line("enter an age: ")
        .parse()
        .expect("Please enter a valid integer for age");

    // ---------------------------------------------------------------------
    // 4. Execute INSERT with named parameters
    // ---------------------------------------------------------------------
    conn.exec_drop(
        "INSERT INTO students (name, age) VALUES (:name, :age)",
        mysql::params! {
            "name" => &name,
            "age"  => age,
        },
    )?;

    // ---------------------------------------------------------------------
    // 5. Report affected rows (same as JavaScript “result.affectedRows”)
    // ---------------------------------------------------------------------
    println!("Number of records inserted: {}", conn.affected_rows());

    Ok(())
}

/// Helper that prints a prompt and returns the trimmed input line.
fn read_line(prompt: &str) -> String {
    print!("{prompt}");
    // Ensure the prompt is shown before blocking on stdin
    io::stdout().flush().expect("Failed to flush stdout");

    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read from stdin");
    input.trim_end().to_owned()
}