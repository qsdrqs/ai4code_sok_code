use std::fs::File;
use std::path::Path;

fn thing(path: &str) -> Option<File> {
    if path.contains("/safedir") {
        // Attempt to open the file
        match File::open(path) {
            Ok(file) => Some(file),
            Err(_) => {
                println!("Failed to open the file.");
                None
            }
        }
    } else {
        // Panic equivalent
        println!("invalid path");
        None
    }
}

fn main() {
    // Test good path
    let f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    println!("\nResult 1:");
    match f {
        Some(_) => println!("File opened successfully."),
        None => println!("Failed to open file."),
    }

    // Test bad path
    let f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    println!("\nResult 2:");
    match f {
        Some(_) => println!("File opened successfully."),
        None => println!("Failed to open file."),
    }
}