use std::collections::HashMap;

// Define a struct to hold student data
struct Student {
    name: String,
    age: u8,
}

// Define a struct to manage students
struct StudentManager {
    students: HashMap<String, u8>,
}

impl StudentManager {
    fn new() -> Self {
        StudentManager {
            students: HashMap::new(),
        }
    }

    // Method to add a student
    fn add(&mut self, name: String, age: u8) {
        self.students.insert(name, age);
    }
}

fn my_function(name: String, age: u8, students: &mut StudentManager) {
    students.add(name, age);
}

fn main() {
    let mut students = StudentManager::new();
    my_function("John Doe".to_string(), 20, &mut students);
}