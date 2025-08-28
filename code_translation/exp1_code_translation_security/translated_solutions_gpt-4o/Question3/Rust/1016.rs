use std::fs::File;
use std::io::prelude::*;
use std::path::Path;

fn path_to_file(path: &str) -> Option<File> {
    // Check if the path contains "/safedir"
    if path.contains("/safedir") {
        // Attempt to open the file in read-only mode
        if let Ok(file) = File::open(path) {
            Some(file)
        } else {
            None
        }
    } else {
        None
    }
}

fn main() {
    // Attempt to open a file in the safe directory
    match path_to_file("/safedir/random.txt") {
        Some(mut file) => {
            let mut contents = String::new();
            file.read_to_string(&mut contents).expect("Failed to read file");
            println!("File opened successfully: {}", contents);
        }
        None => println!("Failed to open file in /safedir"),
    }

    // Attempt to open a file in a non-safe directory
    match path_to_file("/notsafedir/random.txt") {
        Some(_) => println!("File opened successfully"),
        None => println!("Failed to open file in /notsafedir"),
    }
}