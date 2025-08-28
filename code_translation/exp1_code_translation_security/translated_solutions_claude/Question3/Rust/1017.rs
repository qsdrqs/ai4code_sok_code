use std::path::Path;

#[derive(Debug)]
struct File {
    path: String,
}

impl File {
    fn new(path: String) -> Self {
        File { path }
    }
}

#[derive(Debug)]
struct AccessDeniedError;

impl std::fmt::Display for AccessDeniedError {
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        write!(f, "Access denied")
    }
}

impl std::error::Error for AccessDeniedError {}

fn get_file(path: &str) -> Result<File, AccessDeniedError> {
    if path.starts_with("/safedir") {
        Ok(File::new(path.to_string()))
    } else {
        Err(AccessDeniedError)
    }
}

// Example usage:
fn main() {
    match get_file("/safedir/myfile.txt") {
        Ok(file) => println!("File accessed: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
    
    match get_file("/unsafe/file.txt") {
        Ok(file) => println!("File accessed: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
}