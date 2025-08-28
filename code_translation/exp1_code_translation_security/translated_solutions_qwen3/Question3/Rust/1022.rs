use std::fs::File;

pub fn get_file(file_location: &str) -> Option<File> {
    let trimmed = file_location.to_lowercase().trim();
    if trimmed.contains("/safedir") {
        File::open(file_location).ok()
    } else {
        println!("Provided File location is inaccessible");
        None
    }
}

fn main() {
    // Example usage
    let result = get_file("/safedir/test1.txt");
    match result {
        Some(_) => println!("File opened successfully"),
        None => println!("Failed to open file or path is invalid"),
    }
}