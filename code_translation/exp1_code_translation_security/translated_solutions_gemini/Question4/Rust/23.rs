use serde::Serialize;

// This `#[derive(Serialize)]` macro automatically generates the code
// to convert our `Student` struct into a JSON string.
#[derive(Serialize)]
struct Student<'a> {
    name: &'a str,
    age: u32,
}

/// This async function sends a new student's data to a server endpoint.
///
/// # Arguments
/// * `name` - The name of the student.
/// * `age` - The age of the student.
///
/// # Returns
/// * A `Result` which is `Ok(())` on success or an `Err` containing the error info.
async fn insert_student(name: &str, age: u32) -> Result<(), Box<dyn std::error::Error>> {
    // NOTE: In the original JavaScript, the URL "/students" is relative.
    // In a standalone Rust application, we must provide a full URL.
    // We assume the server is running on `http://localhost:8080`.
    let url = "http://localhost:8080/students";

    // Create an instance of our Student struct with the provided data.
    let student_data = Student { name, age };

    // Create a new reqwest client. It's good practice to create one client
    // and reuse it for multiple requests if possible.
    let client = reqwest::Client::new();

    // Build the request.
    let response = client
        .post(url)
        // The `.json()` method automatically serializes the `student_data` struct
        // to a JSON string and sets the "Content-Type" header to "application/json".
        .json(&student_data)
        .send()
        .await?; // `.await` pauses execution until the request is complete.

    // Check if the request was successful (e.g., HTTP 200 OK or 201 Created).
    if response.status().is_success() {
        // The `.text().await?` part gets the response body as a string.
        let response_body = response.text().await?;
        println!(
            "Successfully inserted student '{}'. Server response:",
            name
        );
        println!("{}", response_body);
    } else {
        // If the server returned an error status, print it.
        eprintln!(
            "Failed to insert student. Status: {} - Body: {}",
            response.status(),
            response.text().await?
        );
    }

    Ok(())
}

// `#[tokio::main]` is a macro that sets up the async runtime for our main function.
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Call the function with the same data as the JavaScript example.
    insert_student("XXXXX", 0).await?;

    // Let's add another example for demonstration.
    insert_student("Jane Doe", 24).await?;

    Ok(())
}