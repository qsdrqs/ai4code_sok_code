use std::collections::HashMap;

// Mock Connection struct to simulate database connection
struct Connection;

impl Connection {
    fn new() -> Self {
        Connection
    }
    
    fn query(&self, query: &str) -> Result<String, String> {
        // Mock implementation - in real code this would execute the query
        println!("Executing query: {}", query);
        Ok("Query executed successfully".to_string())
    }
}

// Student struct to hold student data
struct Student {
    name: String,
    age: u32,
}

fn register_student(name: String, age: u32) -> Result<String, String> {
    let student = Student {
        name,
        age,
    };
    
    let connection = Connection::new();
    let query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')", 
        student.name, 
        student.age
    );
    
    connection.query(&query)
}

// Example usage
fn main() {
    match register_student("John Doe".to_string(), 20) {
        Ok(result) => println!("Success: {}", result),
        Err(error) => println!("Error: {}", error),
    }
}