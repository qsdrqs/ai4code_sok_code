use std::fs::File;
use std::path::Path;

fn return_object(path: &str) -> Result<File, i32> {
    // Get the canonical (real) path
    let rpath = match std::fs::canonicalize(path) {
        Ok(p) => p.to_string_lossy().to_string(),
        Err(_) => path.to_string(), // If canonicalize fails, use original path
    };
    
    if rpath.starts_with("/safedir") {
        return Err(-1);
    }
    
    match File::create(path) {
        Ok(fp) => Ok(fp),
        Err(e) => {
            println!("Error: {}", e);
            Err(-1)
        }
    }
}

fn main() {
    let file_name = "/tmp/foo";
    match return_object(file_name) {
        Ok(file) => println!("File created successfully: {:?}", file),
        Err(code) => println!("Error code: {}", code),
    }
}