use std::fs::File;
use std::io::Result as IoResult;

const ALLOWED_PATH: &str = "/safedir";

fn get_file(path: &str) -> Result<File, String> {
    if !path.starts_with(ALLOWED_PATH) {
        return Err("Not allowed to get file from this dir".to_string());
    }
    
    File::open(path).map_err(|e| format!("Failed to open file: {}", e))
}

fn main() {
    match get_file("/safedir/test/path") {
        Ok(file) => println!("File opened successfully"),
        Err(e) => println!("Error: {}", e),
    }
}