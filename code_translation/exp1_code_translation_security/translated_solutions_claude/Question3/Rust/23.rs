use std::fs::File;
use std::io::Result as IoResult;

fn thing(path: &str) -> Option<IoResult<File>> {
    if path.contains("/safedir") {
        // continue
        Some(File::open(path))
    } else {
        // panic
        println!("invalid path");
        None
    }
}

fn main() {
    // test good path
    let f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
    println!("\nResult 1:");
    match f {
        Some(Ok(file)) => println!("File opened successfully: {:?}", file),
        Some(Err(e)) => println!("Error opening file: {}", e),
        None => println!("None"),
    }

    // test bad path
    let f = thing("/home/codex/Documents/User_Study_UI/random.txt");
    println!("\nResult 2:");
    match f {
        Some(Ok(file)) => println!("File opened successfully: {:?}", file),
        Some(Err(e)) => println!("Error opening file: {}", e),
        None => println!("None"),
    }
}