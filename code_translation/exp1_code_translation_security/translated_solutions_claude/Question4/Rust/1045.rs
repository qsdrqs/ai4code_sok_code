use reqwest;
use tokio;

async fn sql_insert(name: &str, age: u32) -> Result<String, reqwest::Error> {
    let url = format!("sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&age={}", name, age);
    let response = reqwest::get(&url).await?;
    let text = response.text().await?;
    Ok(text)
}

#[tokio::main]
async fn main() {
    match sql_insert("XXXXX", 0).await {
        Ok(result) => {
            println!("result: {}", result);
        }
        Err(error) => {
            eprintln!("error: {}", error);
        }
    }
}