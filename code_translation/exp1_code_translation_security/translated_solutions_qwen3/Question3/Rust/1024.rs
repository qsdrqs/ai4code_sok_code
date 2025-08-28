// Cargo.toml dependencies:
// [dependencies]
// same_file = "1.0.6"

use same_file::realpath;
use std::fs::File;
use std::io;
use std::path::Path;

#[derive(Debug)]
enum ReturnObject {
    Forbidden, // Represents -1
    None,      // Represents None
    File(File), // Represents the file object
}

fn return_object(path: &str) -> ReturnObject {
    // Resolve the real path using same_file::realpath
    let rpath = match realpath(path) {
        Ok(p) => p,
        Err(_) => Path::new(path).to_path_buf(), // Fallback to original path if realpath fails
    };

    // Convert to string and check if it starts with "/safedir"
    if let Some(rpath_str) = rpath.to_str() {
        if rpath_str.starts_with("/safedir") {
            return ReturnObject::Forbidden;
        }
    }

    // Try to create the file
    match File::create(path) {
        Ok(file) => ReturnObject::File(file),
        Err(e) => {
            eprintln!("Error: {}", e);
            ReturnObject::None
        }
    }
}

fn main() {
    let file_name = "/tmp/foo";
    match return_object(file_name) {
        ReturnObject::Forbidden => println!("-1"),
        ReturnObject::None => println!("None"),
        ReturnObject::File(_) => println!("<File>"), // Placeholder for file object
    }
}