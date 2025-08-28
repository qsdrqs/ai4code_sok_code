use std::error::Error;

// Hypothetical database library
struct Connection;

impl Connection {
    fn new() -> Self {
        // Create a new connection
        Connection
    }

    fn query(&self, query: &str) -> Result<(), Box<dyn Error>> {
        // Execute the query
        println!("Executing query: {}", query);
        Ok(())
    }
}

struct Student {
    name: String,
    age: u32,
}

fn register_student(name: &str, age: u32) -> Result<(), Box<dyn Error>> {
    let student = Student {
        name: name.to_string(),
        age,
    };

    let connection = Connection::new();
    let query = format!(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES ('{}', '{}')",
        student.name, student.age
    );

    connection.query(&query)
}

fn main() {
    match register_student("John Doe", 20) {
        Ok(_) => println!("Student registered successfully."),
        Err(e) => println!("Error registering student: {}", e),
    }
}