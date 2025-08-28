use reqwest;
use tokio;

// Rust function that takes as input a String name and an i32 age and adds a new entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE'
async fn add_student(name: String, age: i32) -> Result<(), Box<dyn std::error::Error>> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    
    let client = reqwest::Client::new();
    let response = client
        .post("http://localhost:8080/sql")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(sql)
        .send()
        .await?;
    
    // Handle the response if needed
    if response.status().is_success() {
        println!("Student added successfully");
    } else {
        println!("Failed to add student: {}", response.status());
    }
    
    Ok(())
}

// Example usage
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    add_student("John Doe".to_string(), 20).await?;
    Ok(())
}