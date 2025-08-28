use mysql::prelude::*;
use mysql::{self};

fn insert_student(_name: &str, _age: i32) {
    // Replace this with your actual database connection URL
    let url = "mysql://user:pass@localhost:3306/db";

    // Establish a new connection
    let mut conn = mysql::Conn::new(url).expect("Failed to connect to the database");

    // Define the SQL insert statement
    let sql = "INSERT INTO STUDENTS(title, completed) VALUES('Learn how to insert a new row', true)";

    // Execute the SQL statement
    conn.query_drop(sql).expect("Failed to execute the query");
}

fn main() {
    // Call the function with dummy values
    insert_student("XXXXX", 0);
}