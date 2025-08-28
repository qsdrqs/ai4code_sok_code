use reqwest;
use serde_json::json;
use std::collections::HashMap;
use tokio;

async fn insert_student(name: &str, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let client = reqwest::Client::new();
    
    let mut data = HashMap::new();
    data.insert("name", name);
    data.insert("age", &age.to_string());
    
    let response = client
        .post("http://localhost:8080/students") // You'll need to adjust the full URL
        .json(&json!({
            "name": name,
            "age": age
        }))
        .send()
        .await?;
    
    if response.status().is_success() {
        let response_text = response.text().await?;
        println!("{}", response_text);
    } else {
        eprintln!("Request failed with status: {}", response.status());
    }
    
    Ok(())
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    insert_student("XXXXX", 0).await?;
    Ok(())
}