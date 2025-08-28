use reqwest::Error;

/// Makes an asynchronous GET request to insert a student record.
///
/// This function is the Rust equivalent of the JavaScript `sqlInsert` function.
/// - It is `async`, making it return a `Future`, which is conceptually similar to a JavaScript `Promise`.
/// - It returns a `Result<String, Error>`, which means it will either succeed with a `String`
///   (like `resolve(responseText)`) or fail with an `Error` (like `reject(error)`).
async fn sql_insert(name: &str, age: u32) -> Result<String, Error> {
    // In Rust, we often use the `format!` macro for simple string building.
    // Note: The original JS used a relative URL. In a standalone Rust application,
    // you typically need a full URL, so we've assumed `http://localhost`.
    // We also kept the non-standard "&&" separator to match the original code.
    let url = format!(
        "http://localhost/sqlapi.php?action=INSERTINTOSTUDENTS&&name={}&&age={}",
        name, age
    );

    // `reqwest::get` creates and sends the GET request.
    // The `.await` keyword pauses the function until the request is sent and a response
    // starts coming back. This is like the `onreadystatechange` callback logic.
    let response = reqwest::get(&url).await?;

    // The `?` operator is used for error handling. If `reqwest::get` returns an error,
    // `sql_insert` will immediately stop and return that error, similar to `reject()`.

    // We then wait for the full response body to be downloaded as text.
    // The `.await` pauses again, and the `?` handles any potential errors during download.
    let body = response.text().await?;

    // If both operations succeed, we return the body wrapped in `Ok`,
    // which is the equivalent of `resolve(this.responseText)`.
    Ok(body)
}

// `#[tokio::main]` sets up the asynchronous runtime needed to execute `async` functions.
// This is the entry point of our application.
#[tokio::main]
async fn main() {
    // This block is the equivalent of the `.then()` and `.catch()` chain.
    println!("Attempting to insert record...");

    // We call our async function and `.await` its result.
    // The `match` statement handles the `Result` returned by `sql_insert`.
    match sql_insert("XXXXX", 0).await {
        // The `Ok(result)` arm runs if the Future succeeded, like a `.then()` block.
        Ok(result) => {
            println!("result: {}", result);
        }
        // The `Err(error)` arm runs if the Future failed, like a `.catch()` block.
        Err(error) => {
            eprintln!("error: {}", error);
        }
    }
}