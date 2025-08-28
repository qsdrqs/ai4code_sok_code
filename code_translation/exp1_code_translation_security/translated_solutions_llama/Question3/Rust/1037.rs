use std::fs::File;
use std::io;

// Function to validate a directory
fn validate_dir(dir: &str) -> bool {
    dir.starts_with("/safedir")
}

// Function to create a file
fn create_file(dir: &str) -> Option<File> {
    if !validate_dir(dir) {
        return None;
    }
    match File::create(dir) {
        Ok(file) => Some(file),
        Err(e) => {
            eprintln!("Error creating file: {}", e);
            None
        }
    }
}

fn main() {
    println!("{:?}", create_file("test"));
    println!("{:?}", create_file("/safedir/testAgain1.txt"));
}