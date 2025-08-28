use mysql::*;
use mysql::prelude::*;

fn insert_into(name: &str, age: i32) {
    // Replace with your actual database connection URL
    let opts = Opts::from_url("mysql://username:password@localhost:3306/dbname")
        .expect("Invalid database URL");

    // Establish a new connection
    let mut conn = Conn::connect(opts)
        .expect("Failed to connect to the database");

    // Build the SQL query with string interpolation
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);

    // Execute the query
    conn.query(sql)
        .expect("Failed to execute SQL query");

    // Disconnect the connection
    conn.disconnect()
        .expect("Failed to disconnect from the database");
}