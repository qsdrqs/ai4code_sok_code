use mysql::prelude::*;
use mysql::*;
use std::io::{self, Write};

// A helper function to read a line of text from the console.
// This is equivalent to the `readLine` function in the JS example.
fn read_string(prompt: &str) -> String {
    print!("{}", prompt);
    // We need to flush stdout to ensure the prompt is displayed before reading input.
    io::stdout().flush().expect("Failed to flush stdout");

    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");
    
    // Trim whitespace (including the newline character) from the input.
    input.trim().to_string()
}

// A helper function to read an integer from the console.
// It will loop until a valid integer is provided.
// This is equivalent to the `readInt` function in the JS example.
fn read_int(prompt: &str) -> i32 {
    loop {
        let input_str = read_string(prompt);
        match input_str.parse::<i32>() {
            Ok(num) => return num,
            Err(_) => println!("Invalid input. Please enter a valid integer."),
        }
    }
}

// The main function now returns a Result, which is the standard way
// to handle potential errors in Rust applications.
fn main() -> Result<()> {
    println!("Connecting to database...");

    // Configure the connection options.
    // This is equivalent to the object passed to mysql.createConnection.
    let opts = OptsBuilder::new()
        .ip_or_hostname(Some("localhost"))
        .user(Some("yourusername"))
        .pass(Some("yourpassword"))
        .db_name(Some("mydb"));

    // Establish the connection. The `?` operator will automatically
    // propagate any connection errors, similar to `if (err) throw err;`.
    let mut conn = Conn::new(opts)?;

    println!("Connected!");

    // The SQL statement. The `mysql` crate uses `?` as placeholders
    // for parameters to prevent SQL injection.
    let sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Get user input using our helper functions.
    let name = read_string("Enter a name: ");
    let age = read_int("Enter an age: ");

    // Execute the prepared statement with the provided values.
    // The `exec_drop` method is used for statements that don't return rows (like INSERT).
    // It still returns a `QueryResult` which we can inspect.
    let result = conn.exec_iter(sql, (name, age))?;

    println!("Number of records inserted: {}", result.affected_rows());

    // If everything succeeds, return Ok.
    Ok(())
}