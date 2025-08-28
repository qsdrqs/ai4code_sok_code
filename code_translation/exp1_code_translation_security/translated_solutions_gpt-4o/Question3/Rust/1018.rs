use std::fs::File;
use std::io;
use std::path::Path;

fn read_in(path: &str) -> io::Result<Option<File>> {
    if path.contains("/safedir") {
        File::open(path).map(Some)
    } else {
        Ok(None)
    }
}

fn main() {
    // Example usage
    match read_in("/safedir/example.txt") {
        Ok(Some(file)) => {
            println!("File opened successfully: {:?}", file);
        }
        Ok(None) => {
            println!("Path does not contain '/safedir'.");
        }
        Err(e) => {
            println!("Error opening file: {}", e);
        }
    }
}