use std::fs::File;
use std::io::{self, Read, Write};

// Function to open a file in read mode
fn open_file(file_path: &str) -> File {
    File::open(file_path).expect("Failed to open file")
}

fn main() {
    // Define the file path
    let file_path = "/home/codex/Documents/User_Study_UI/prac.txt";

    // Write to the file
    {
        let mut file = File::create(file_path).expect("Failed to create file");
        file.write_all(b"Hello it's Melo").expect("Failed to write to file");
    } // File is automatically closed when it goes out of scope

    // Read from the file
    let mut file = open_file(file_path);
    let mut contents = String::new();
    file.read_to_string(&mut contents).expect("Failed to read file");

    // Print the contents of the file
    println!("{}", contents);
}