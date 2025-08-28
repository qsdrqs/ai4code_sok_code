use mysql::*;
use mysql::prelude::*;

/// Adds a student to the `STUDENTS` table in the MySQL database.
///
/// # Arguments
///
/// * `name` - A string slice that holds the student's name.
/// * `age`  - An unsigned 32-bit integer representing the student's age.
///
/// # Returns
///
/// * `Ok(())` if the student was successfully inserted.
/// * `Err(mysql::Error)` if any database operation fails.
pub fn add_student(name: &str, age: u32) -> mysql::Result<()> {
    // Establish a connection pool to the MySQL database.
    // This is equivalent to `mysql.createConnection()` in JavaScript.
    let pool = Pool::new("mysql://yourusername:yourpassword@localhost:3306/mydb")?;

    // Get a connection from the pool.
    // This is equivalent to `con.connect()` in JavaScript.
    let mut conn = pool.get_conn()?;
    println!("Connected!");

    // Execute the INSERT query using parameterized SQL to prevent SQL injection.
    // This is safer than string concatenation used in the original JavaScript.
    conn.exec_drop(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
        (name, age),
    )?;

    // Log the successful insertion.
    println!("1 record inserted");

    Ok(())
}