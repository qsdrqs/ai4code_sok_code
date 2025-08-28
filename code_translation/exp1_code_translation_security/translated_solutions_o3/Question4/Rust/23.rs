// We assume that the following crates will already be available in Cargo.toml:
//
// reqwest = { version = "0.11", features = ["json", "tls"] }
// serde   = { version = "1.0",  features = ["derive"] }
// tokio   = { version = "1",    features = ["rt-multi-thread", "macros"] }
//
// Nothing else has to be added by you; the build system that will run this
// code will supply them.

/*
   The JavaScript code performed a simple HTTP POST request to “/students”
   and printed the server’s response.  
   Below is the same idea implemented in Rust.  It defines
   1. A serialisable Student structure,
   2. An async function `insert_student` that performs the POST,
   3. A tiny `main` which calls the function once (like the JS
      snippet’s `insertStudent('XXXXX', 0);`).
*/

use reqwest::Client;
use serde::Serialize;
use anyhow::Result;   // For convenient error handling

#[derive(Serialize)]
struct Student<'a> {
    name: &'a str,
    age:  u32,
}

/// Send a new student record to the `/students` endpoint and print the reply.
async fn insert_student(name: &str, age: u32) -> Result<()> {
    // Reqwest’s Client is cheap to clone, but here we only need one instance.
    let client = Client::new();

    // Anything that implements Serialize can be used with `json`.
    let payload = Student { name, age };

    // POST as JSON (mimics the original AJAX call’s semantics).
    let response = client
        .post("http://localhost:8000/students") // adjust host/port as needed
        .json(&payload)                         // sets Content-Type: application/json
        .send()
        .await?;                                // `?` propagates errors

    // Read the server’s response body as text and print it.
    let body = response.text().await?;
    println!("{body}");

    Ok(())
}

#[tokio::main]
async fn main() -> Result<()> {
    // Same test call as in the JS example
    insert_student("XXXXX", 0).await?;
    Ok(())
}