use mysql::*;
use mysql::prelude::*;

/// Inserts a new student record into the database.
///
/// # Arguments
///
/// * `name` - A string slice that holds the student's name.
/// * `age` - An integer representing the student's age.
///
fn insert(name: &str, age: i32) {
    // Construct the database connection URL
    let url = "mysql://yourusername:yourpassword@localhost/STUDENTS";

    // Parse the URL into connection options
    let opts = Opts::from_url(url).expect("Invalid database URL");

    // Establish a new connection
    let mut conn = Conn::connect(opts).expect("Failed to connect to database");

    // Log successful connection
    println!("Connected!");

    // Define the SQL query with parameterized placeholders
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Execute the query with the provided parameters
    conn.exec_drop(sql, (name, age))
        .expect("Failed to insert record");

    // Log successful insertion
    println!("1 record inserted");
}