use mysql::{prelude::*, *};

/// Inserts a single row into the `students` table.
///
/// # Arguments
/// * `name` – Student's name.
/// * `age`  – Student's age.
///
/// # Errors
/// Returns an error if the connection or the query fails.
pub fn insert(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    // mysql://<user>:<password>@<host>:<port>/<database>
    let url = "mysql://yourusername:yourpassword@localhost:3306/STUDENTS";

    // Open a connection (a pool is used so you can cheaply reuse connections if you want).
    let pool = Pool::new(url)?;
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    // Execute the insert statement using named parameters to avoid SQL-injection problems.
    conn.exec_drop(
        "INSERT INTO students (name, age) VALUES (:name, :age)",
        params! {
            "name" => name,
            "age"  => age,
        },
    )?;

    println!("1 record inserted");
    Ok(())
}