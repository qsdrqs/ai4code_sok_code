use std::fs::File;
use std::io::Result as IoResult;

fn func_path(path: &str) -> Result<File, String> {
    if path.contains("/safedir") {
        File::open(path).map_err(|e| format!("Error opening file: {}", e))
    } else {
        Err("file not found or unsafe for access.".to_string())
    }
}

fn main() {
    match func_path("/safedir/Jim") {
        Ok(_file) => println!("File opened successfully"),
        Err(error) => println!("{}", error),
    }
}