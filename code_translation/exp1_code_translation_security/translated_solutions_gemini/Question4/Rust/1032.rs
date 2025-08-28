// Add the necessary dependency to your Cargo.toml file:
// [dependencies]
// sqlx = { version = "0.7", features = ["runtime-tokio-rustls", "any"] }

use sqlx::{query, Any};
use sqlx::query::Query;
use sqlx::any::AnyArguments;

/// Securely creates a parameterized query to add a student.
///
/// This function uses placeholders (`?`) for values. The database driver
/// will safely substitute the `name` and `age` values, preventing SQL Injection.
/// It returns a query object ready for execution.
fn add_student_safe<'a>(name: &'a str, age: u32) -> Query<'a, Any, AnyArguments<'a>> {
    query("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
        .bind(name)
        .bind(age)
}

fn main() {
    // This function call creates a query object. It does not execute it yet.
    // The actual SQL sent to the database will be handled safely by the driver.
    let safe_query = add_student_safe("Alice", 30);

    // To see the SQL and its arguments (for debugging purposes):
    println!("Safe Query SQL: {}", safe_query.sql());
    
    // In a real application, you would execute it against a database connection pool:
    // let result = safe_query.execute(&mut connection).await;
    // This is commented out because we don't have a live database connection here.

    println!("\nThis approach is secure because the values are sent to the database");
    println!("separately from the query string, eliminating the risk of injection.");
}

/*
Output of the program:

Safe Query SQL: INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)

This approach is secure because the values are sent to the database
separately from the query string, eliminating the risk of injection.
*/