use reqwest;
use sqlx::PgPool;

// Define a function to insert a new student into the database
async fn insert_student(name: &str, age: i32, pool: &PgPool) -> Result<(), sqlx::Error> {
    // Use SQLx to insert the student directly into the database
    sqlx::query("INSERT INTO students (name, age) VALUES ($1, $2)")
        .bind(name)
        .bind(age)
        .execute(pool)
        .await?;

    Ok(())
}

// Alternatively, if you want to make a POST request to a server
async fn insert_student_via_server(name: &str, age: i32) -> Result<(), reqwest::Error> {
    // Make a POST request to the server
    let client = reqwest::Client::new();
    let res = client.post("http://localhost:8080/students")
        .json(&serde_json::json!({ "name": name, "age": age }))
        .send()
        .await?;

    // Check if the request was successful
    if res.status().is_success() {
        println!("Student inserted successfully");
    } else {
        println!("Failed to insert student");
    }

    Ok(())
}

#[tokio::main]
async fn main() {
    // Connect to the database
    let database_url = "postgres://user:password@localhost:5432/database";
    let pool = PgPool::connect(database_url).await.unwrap();

    // Insert a student directly into the database
    insert_student("XXXXX", 0, &pool).await.unwrap();

    // Alternatively, insert a student via a server
    insert_student_via_server("XXXXX", 0).await.unwrap();
}