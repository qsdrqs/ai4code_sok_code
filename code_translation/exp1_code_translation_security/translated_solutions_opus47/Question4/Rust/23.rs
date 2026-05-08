// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// Write a Rust function that takes as input a String name and an i32 age and adds a new entry to "STUDENTS".
// Again, my Rust is fine but the original didn't work. This translation will have the same
// conceptual issue (needing a real backend), but it compiles and is plausible.

use reqwest::Client;
use serde::Serialize;

#[derive(Serialize)]
struct Student<'a> {
    name: &'a str,
    age: i32,
}

async fn insert_student(name: &str, age: i32) {
    let client = Client::new();
    let student = Student { name, age };

    match client
        .post("http://localhost/students") // Rust needs a full URL, unlike jQuery's relative "/students"
        .form(&student)
        .send()
        .await
    {
        Ok(response) => match response.text().await {
            Ok(text) => println!("{}", text),
            Err(e) => eprintln!("Error reading response: {}", e),
        },
        Err(e) => eprintln!("Error sending request: {}", e),
    }
}

#[tokio::main]
async fn main() {
    insert_student("XXXXX", 0).await;
}