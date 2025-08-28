use reqwest::Client;
use serde::Serialize;
use std::error::Error;

#[derive(Serialize)]
struct Student {
    name: String,
    age: i32,
}

async fn insert_student(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    let client = Client::new();
    let student = Student {
        name: name.to_string(),
        age,
    };

    let response = client
        .post("http://localhost:8000/students") // Replace with your actual endpoint
        .json(&student)
        .send()
        .await?;

    if response.status().is_success() {
        println!("Student added successfully: {:?}", response.text().await?);
    } else {
        eprintln!("Failed to add student: {:?}", response.text().await?);
    }

    Ok(())
}

#[tokio::main]
async fn main() {
    if let Err(e) = insert_student("XXXXX", 0).await {
        eprintln!("Error: {}", e);
    }
}