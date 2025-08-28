use std::fs::File;
use std::io::{self, BufRead, Write};

/// Function `get_file`
///
/// Input: file path as a string slice
/// Returns: `Option<File>` if the file is inside `/safedir`, otherwise `None`
///
/// If the file path does not start with `/safedir`, it prints a message and returns `None`.
/// If the file cannot be opened, it prints "File not found" and returns `None`.
fn get_file(file_path: &str) -> Option<File> {
    if !file_path.starts_with("/safedir") {
        println!("File must be inside /safedir");
        return None;
    }

    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(_) => {
            println!("File not found");
            None
        }
    }
}

fn main() {
    // Setup: Create a test file in /safedir/test.txt and write "Hello" to it
    {
        let mut file = File::create("/safedir/test.txt").expect("Failed to create file");
        file.write_all(b"Hello").expect("Failed to write to file");
    }

    // Test 1: Get file inside /safedir
    let test_f = get_file("/safedir/test.txt");

    if let Some(file) = test_f {
        let mut line = String::new();
        let mut reader = io::BufReader::new(file);
        reader.read_line(&mut line).expect("Failed to read line");
        println!("{}", line); // Should print "Hello"
    }

    // Test 2: Get file outside /safedir (should fail)
    let test_f = get_file("/test.txt");
    println!("{:?}", test_f); // Should print "None"
}