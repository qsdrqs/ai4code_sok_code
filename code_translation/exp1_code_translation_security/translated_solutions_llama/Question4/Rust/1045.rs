use reqwest;

// Define an async function for SQL insert
async fn sql_insert(name: &str, age: i32) -> Result<String, reqwest::Error> {
    let url = format!("sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&&age={}", name, age);
    let response = reqwest::get(url).await?;
    let text = response.text().await?;
    Ok(text)
}

// Define the main function
#[tokio::main]
async fn main() {
    // Call sql_insert with example parameters
    let result = sql_insert("XXXXX", 0).await;

    // Handle the result
    match result {
        Ok(text) => println!("result: {}", text),
        Err(error) => eprintln!("error: {}", error),
    }
}