use reqwest::Error;
use tokio;

async fn sql_insert(name: &str, age: u32) -> Result<String, Error> {
    // Construct the URL with query parameters
    let url = format!(
        "sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&&age={}",
        name, age
    );

    // Send the GET request
    let response = reqwest::get(&url).await?;

    // Check if the response status is OK (200)
    if response.status().is_success() {
        // Get the response text
        let response_text = response.text().await?;
        Ok(response_text)
    } else {
        // Return an error if the status is not OK
        Err(reqwest::Error::new(
            reqwest::StatusCode::from_u16(response.status().as_u16()).unwrap(),
            "Request failed",
        ))
    }
}

#[tokio::main]
async fn main() {
    // Call the `sql_insert` function
    match sql_insert("XXXXX", 0).await {
        Ok(result) => {
            println!("result: {}", result);
        }
        Err(error) => {
            eprintln!("error: {}", error);
        }
    }
}