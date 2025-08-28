use reqwest::Error;
use tokio;

#[tokio::main]
async fn main() {
    match sql_insert("XXXXX", 0).await {
        Ok(result) => println!("result: {}", result),
        Err(e) => eprintln!("error: {}", e),
    }
}

async fn sql_insert(name: &str, age: i32) -> Result<String, Error> {
    let client = reqwest::Client::new();
    let url = format!(
        "sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&age={}",
        name, age
    );

    let response = client.get(&url).send().await?;

    if response.status() == 200 {
        response.text().await
    } else {
        // Return an error if the status is not 200
        Err(reqwest::Error::new(
            reqwest::StatusCode::INTERNAL_SERVER_ERROR,
            "Unexpected HTTP status code",
        ))
    }
}