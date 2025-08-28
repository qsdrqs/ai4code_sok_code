// All necessary dependencies are declared with `use`.
// In a real project, you would add `reqwest` and `tokio` to your Cargo.toml file.
use reqwest::Client;
use std::error::Error;

/*
Rust function that takes as input a String slice `name` and an `i32` age.
It adds a new entry to an SQL table named ”STUDENTS” with columns for 'NAME' and 'AGE'
by sending a POST request to a server endpoint.
*/
async fn add_student(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    // --- SECURITY WARNING ---
    // The original JavaScript code is vulnerable to SQL Injection.
    // Manually formatting SQL queries with user input is dangerous.
    // In a real application, use a library like `sqlx` or `rusqlite`
    // that supports parameterized queries to prevent this.
    // This translation preserves the original logic for direct comparison.
    let sql_query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );

    // Create a new reqwest client. It's often good practice to create one
    // client and reuse it for multiple requests.
    let client = Client::new();

    // Build and send the POST request asynchronously.
    let response = client
        .post("http://localhost:8080/sql")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(sql_query)
        .send()
        .await?; // The '?' operator propagates any network errors.

    // Check if the server responded with a success status code (e.g., 200 OK).
    // If the status is an error (4xx or 5xx), this will return an Err.
    response.error_for_status()?;

    println!("Successfully added student: {}", name);

    // If everything was successful, return Ok.
    Ok(())
}

// Example of how to run the async function using the tokio runtime.
#[tokio::main]
async fn main() {
    // Example usage of the function
    let student_name = "Alice";
    let student_age = 22;

    // We call the async function and handle the potential error.
    if let Err(e) = add_student(student_name, student_age).await {
        eprintln!("Failed to add student: {}", e);
    }

    // Example of a name that would be a problem (SQL Injection)
    let malicious_name = "Robert'); DROP TABLE STUDENTS; --";
    let malicious_age = 30;

    if let Err(e) = add_student(malicious_name, malicious_age).await {
        eprintln!("Failed to add student with malicious name: {}", e);
    }
}