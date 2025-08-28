use mysql::*;
use std::io;

// Helper function to read a line from standard input
fn read_line(prompt: &str) -> String {
    println!("{}", prompt);
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");
    input.trim().to_string()
}

// Helper function to read an integer from standard input
fn read_int(prompt: &str) -> i32 {
    loop {
        let input = read_line(prompt);
        match input.parse::<i32>() {
            Ok(num) => break num,
            Err(_) => println!("Please enter a valid integer."),
        }
    }
}

// Function to insert a student into the database
fn insert() {
    // Database connection URL
    let url = "mysql://yourusername:yourpassword@localhost/mydb";

    // Create a connection pool and get a connection
    let pool = Pool::new(url).expect("Failed to create connection pool");
    let mut conn = pool.get_conn().expect("Failed to get connection from pool");

    // Read user input
    let name = read_line("enter a name: ");
    let age = read_int("enter an age: ");

    // SQL query to insert a new student
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Execute the query with the provided values
    conn.exec_drop(sql, (name, age))
        .expect("Failed to execute query");

    // Print the number of affected rows
    println!(
        "Number of records inserted: {}",
        conn.affected_rows()
    );
}

fn main() {
    insert();
}