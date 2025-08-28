use reqwest::Client;
use std::error::Error;

async fn add_student(name: &str, age: i32) -> Result<(), Box<dyn Error>> {
    let sql = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);
    let client = Client::new();
    
    let res = client.post("http://localhost:8080/sql")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(sql)
        .send()
        .await?;
    
    println!("Response: {:?}", res.status());
    Ok(())
}

#[tokio::main]
async fn main() {
    match add_student("John Doe", 25).await {
        Ok(_) => println!("Student added successfully."),
        Err(e) => eprintln!("Error adding student: {}", e),
    }
}