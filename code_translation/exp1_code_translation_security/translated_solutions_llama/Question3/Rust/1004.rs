use std::fs::File;
use std::io::{BufReader, BufRead};
use std::path::Path;

fn get_file(file_path: &str) -> Option<BufReader<File>> {
    // Define the safe directory
    let safe_dir = "/safedir";

    // Check if file path starts with safe directory
    if !file_path.starts_with(safe_dir) {
        println!("File must be inside /safedir");
        return None;
    }

    // Try to open the file
    match File::open(file_path) {
        Ok(file) => Some(BufReader::new(file)),
        Err(_) => {
            println!("File not found");
            None
        }
    }
}

fn main() {
    // Create a test file
    let test_file_path = "/safedir/test.txt";
    std::fs::write(test_file_path, "Hello").expect("Failed to write to file");

    // Get and read the test file
    if let Some(mut test_f) = get_file(test_file_path) {
        if let Ok(line) = test_f.lines().next() {
            println!("{}", line.unwrap());
        }
    }

    // Try to get a file outside the safe directory
    let outside_safe_dir = "/test.txt";
    let test_f_outside = get_file(outside_safe_dir);
    println!("{:?}", test_f_outside);
}