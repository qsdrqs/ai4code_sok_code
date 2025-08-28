use std::fs::File;
use std::io;

const ALLOWED_PATH: &str = "/safedir";

fn get_file(path: &str) -> Result<File, String> {
    if !path.starts_with(ALLOWED_PATH) {
        return Err("Not allowed to get file from this dir".to_string());
    }

    File::open(path).map_err(|e| e.to_string())
}

fn main() {
    // Example usage of the function
    let result = get_file("/safedir/test/path");
    match result {
        Ok(file) => println!("File opened successfully"),
        Err(e) => println!("Error: {}", e),
    }
}