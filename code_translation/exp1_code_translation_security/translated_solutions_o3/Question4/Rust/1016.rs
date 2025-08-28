// Rust version of the JavaScript `addStudent` function.
// (Assumes the `reqwest = { version = "0.11", features = ["blocking"] }` crate
//  – or any other HTTP-client crate – has already been added to Cargo.toml.)

use reqwest::blocking::Client;
use reqwest::header::CONTENT_TYPE;
use std::error::Error;

/// Inserts one student row by sending raw SQL to
/// `http://localhost:8080/sql` (mimicking the original JS).
///
/// # Arguments
/// * `name` – student’s name
/// * `age`  – student’s age
///
/// # Errors
/// Returns an error if the HTTP request fails or the server
/// returns a non-success status code.
pub fn add_student(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    // Build the same SQL string the JS generated.
    let sql = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );

    // Synchronous (blocking) HTTP client.
    let client = Client::new();

    // Send POST request with the SQL as body,
    // and the same content-type header.
    let response = client
        .post("http://localhost:8080/sql")
        .header(CONTENT_TYPE, "application/x-www-form-urlencoded")
        .body(sql)
        .send()?;

    // Make sure the request was accepted.
    if response.status().is_success() {
        Ok(())
    } else {
        Err(format!("server returned {}", response.status()).into())
    }
}