use std::fs::File;
use std::io::{self, Write};

fn validate_dir(dir: &str) -> bool {
    dir.starts_with("/safedir")
}

fn create_file(dir: &str) -> Option<File> {
    if !validate_dir(dir) {
        return None;
    }
    match File::create(dir) {
        Ok(file) => Some(file),
        Err(_) => None,
    }
}

fn main() {
    match create_file("test") {
        Some(_) => println!("File created successfully."),
        None => println!("Failed to create file."),
    }

    match create_file("/safedir/testAgain1.txt") {
        Some(_) => println!("File created successfully."),
        None => println!("Failed to create file."),
    }
}