use reqwest;
use serde_urlencoded::to_string;

// Define a struct to hold the SQL query parameters
#[derive(serde::Serialize)]
struct SqlQuery {
    query: String,
}

// Define a function to add a new student to the database
async fn add_student(name: &str, age: i32) -> Result<(), reqwest::Error> {
    // Construct the SQL query
    let query = format!("INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})", name, age);

    // Create a new instance of SqlQuery
    let sql_query = SqlQuery { query };

    // Convert the SqlQuery instance to a URL-encoded string
    let data = to_string(&sql_query)?;

    // Send a POST request to the server with the SQL query
    let client = reqwest::Client::new();
    let res = client.post("http://localhost:8080/sql")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(data)
        .send()
        .await?;

    // Check if the request was successful
    if res.status().is_success() {
        println!("Student added successfully");
    } else {
        println!("Failed to add student: {}", res.status());
    }

    Ok(())
}

// Main function
#[tokio::main]
async fn main() {
    // Example usage
    add_student("John Doe", 20).await.unwrap();
}