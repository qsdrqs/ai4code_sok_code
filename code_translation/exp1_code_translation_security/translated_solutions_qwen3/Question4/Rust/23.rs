use reqwest::Error;
use serde::Serialize;
use tokio::main;

#[derive(Serialize)]
struct Student<'a> {
    name: &'a str,
    age: u32,
}

#[tokio::main]
async fn main() {
    if let Err(e) = insert_student("XXXXX", 0).await {
        eprintln!("Error inserting student: {}", e);
    }
}

async fn insert_student(name: &str, age: u32) -> Result<(), Error> {
    let client = reqwest::Client::new();

    let form_data = Student { name, age };

    let response = client
        .post("http://localhost:8000/students")
        .form(&form_data)
        .send()
        .await?;

    let text = response.text().await?;
    println!("Server response: {}", text);

    Ok(())
}