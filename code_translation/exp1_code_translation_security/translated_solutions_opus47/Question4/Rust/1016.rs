use reqwest::blocking::Client;
use reqwest::header::CONTENT_TYPE;

/// Rust function that takes as input a String name and an int age and adds a new entry
/// to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE'
fn add_student(name: &str, age: i32) -> Result<(), reqwest::Error> {
    let sql = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', {})",
        name, age
    );

    let client = Client::new();
    client
        .post("http://localhost:8080/sql")
        .header(CONTENT_TYPE, "application/x-www-form-urlencoded")
        .body(sql)
        .send()?;

    Ok(())
}