// main.rs
// (Assumes `tokio` and `reqwest` are available in Cargo.toml)

use reqwest::Client;
use std::error::Error;

/// Asynchronously performs `GET sqlapi.php?action=INSERTINTOSTUDENTS&&name=<name>&&age=<age>`
/// and returns the server’s response body.
async fn sql_insert(name: &str, age: u32) -> Result<String, Box<dyn Error + Send + Sync>> {
    let client = Client::new();

    // Mimic the original JavaScript URL (keeps the double ampersands on purpose)
    let url = format!(
        "sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&age={}",
        name, age
    );

    // Perform the HTTP GET request
    let response_text = client.get(&url).send().await?.text().await?;

    Ok(response_text)
}

#[tokio::main]
async fn main() {
    // Equivalent to the JavaScript call: sqlInsert("XXXXX", 0)…
    match sql_insert("XXXXX", 0).await {
        Ok(result) => println!("result {}", result),
        Err(err)   => eprintln!("error {}", err),
    }
}