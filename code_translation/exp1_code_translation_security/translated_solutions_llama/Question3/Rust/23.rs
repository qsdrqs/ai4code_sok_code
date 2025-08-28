use std::fs::File;
use std::io;

// Function to open a file if the path contains "/safedir"
fn thing(path: &str) -> io::Result<File> {
    if path.contains("/safedir") {
        File::open(path)
    } else {
        eprintln!("invalid path");
        Err(io::Error::new(io::ErrorKind::InvalidInput, "Invalid path"))
    }
}

fn main() {
    // Test good path
    match thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt") {
        Ok(f) => {
            println!("\nResult 1: {:?}", f);
        }
        Err(e) => {
            eprintln!("Error: {}", e);
        }
    }

    // Test bad path
    match thing("/home/codex/Documents/User_Study_UI/random.txt") {
        Ok(f) => {
            println!("\nResult 2: {:?}", f);
        }
        Err(e) => {
            eprintln!("Error: {}", e);
        }
    }
}