use std::error::Error;
use std::fmt;
use std::fs::File;
use std::io::{self, ErrorKind};

// Custom error type for path not allowed
#[derive(Debug)]
struct PathNotAllowedError;

impl fmt::Display for PathNotAllowedError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "filepath not allowed, only /safedir/... file paths are accessible")
    }
}

impl Error for PathNotAllowedError {}

// Function to get a file path with validation
fn get_file_path(path: &str) -> Result<File, Box<dyn Error>> {
    if !path.starts_with("/safedir") {
        return Err(Box::new(PathNotAllowedError));
    }
    
    match File::open(path) {
        Ok(file) => Ok(file),
        Err(e) => Err(Box::new(e)),
    }
}

fn main() {
    const PREFIX: &str = "/safedir/";
    let good_paths = vec![
        format!("{}test1.txt", PREFIX),
        format!("{}test.txt", PREFIX),
    ];

    // Test 1: Open valid files
    for path in &good_paths {
        let _file = get_file_path(path).expect("Failed to open valid file");
    }

    // Test 2: File not found error
    match get_file_path("/safedir/noexist.txt") {
        Ok(_) => panic!("Expected FileNotFoundError"),
        Err(e) => {
            if let Some(io_err) = e.downcast_ref::<io::Error>() {
                if io_err.kind() == ErrorKind::NotFound {
                    // Expected error
                } else {
                    panic!("Unexpected error kind: {:?}", io_err.kind());
                }
            } else {
                panic!("Expected IO error, got: {}", e);
            }
        }
    }

    // Test 3: Path not allowed error
    match get_file_path("not/a/good/path.txt") {
        Ok(_) => panic!("Expected PathNotAllowedError"),
        Err(e) => {
            if e.downcast_ref::<PathNotAllowedError>().is_some() {
                // Expected error
            } else {
                panic!("Expected PathNotAllowedError, got: {}", e);
            }
        }
    }
}