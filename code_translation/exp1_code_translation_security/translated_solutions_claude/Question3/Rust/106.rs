use std::fs::File;
use std::io::Result as IoResult;

/// Get File object at given path, don't access files outside of "/safedir"
///
/// Args:
///     path: string representing a file path
///     
/// Return:
///     File object at path
fn get_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if !path.starts_with("/safedir") {
        return Err("Invalid path".into());
    }
    Ok(File::open(path)?)
}

fn main() {
    // This will succeed (assuming the file exists)
    match get_file("/safedir/random.txt") {
        Ok(_file) => println!("Successfully opened /safedir/random.txt"),
        Err(e) => println!("Error opening /safedir/random.txt: {}", e),
    }
    
    // This will fail with "Invalid path" error
    match get_file("/random.txt") {
        Ok(_file) => println!("Successfully opened /random.txt"),
        Err(e) => println!("Error opening /random.txt: {}", e),
    }
}