use std::fs::File;
use std::io::{self, Read};

fn check_if_safe(mystring: &str) -> bool {
    mystring.starts_with("/safedir") && !mystring.contains("/..")
}

fn get_file(filepath: &str) -> io::Result<Option<File>> {
    if check_if_safe(filepath) {
        File::open(filepath).map(Some)
    } else {
        Ok(None)
    }
}

fn main() {
    // Example usage
    match get_file("/safedir/example.txt") {
        Ok(Some(mut file)) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                eprintln!("Error reading file: {}", e);
            } else {
                println!("File contents: {}", contents);
            }
        }
        Ok(None) => println!("File path is not safe."),
        Err(e) => eprintln!("Error opening file: {}", e),
    }
}