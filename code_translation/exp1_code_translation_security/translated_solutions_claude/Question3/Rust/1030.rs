use std::fs::File;
use std::io::Result as IoResult;

// Custom error type for PathNotAllowed
#[derive(Debug)]
struct PathNotAllowed {
    message: String,
}

impl std::fmt::Display for PathNotAllowed {
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        write!(f, "{}", self.message)
    }
}

impl std::error::Error for PathNotAllowed {}

// Custom error enum to handle both PathNotAllowed and IO errors
#[derive(Debug)]
enum FileError {
    PathNotAllowed(PathNotAllowed),
    Io(std::io::Error),
}

impl From<std::io::Error> for FileError {
    fn from(error: std::io::Error) -> Self {
        FileError::Io(error)
    }
}

impl From<PathNotAllowed> for FileError {
    fn from(error: PathNotAllowed) -> Self {
        FileError::PathNotAllowed(error)
    }
}

fn get_file_path(path: &str) -> Result<File, FileError> {
    if !path.starts_with("/safedir") {
        return Err(PathNotAllowed {
            message: "filepath not allowed, only /safedir/... file paths are accessible".to_string(),
        }.into());
    }
    
    let file = File::open(path)?;
    Ok(file)
}

fn main() {
    let prefix = "/safedir/";
    
    let good_paths = vec![
        format!("{}test1.txt", prefix),
        format!("{}test.txt", prefix),
    ];
    
    // should open without exception
    for path in &good_paths {
        match get_file_path(path) {
            Ok(_file) => {
                // File automatically closes when it goes out of scope
                println!("Successfully opened: {}", path);
            }
            Err(e) => {
                println!("Error opening {}: {:?}", path, e);
            }
        }
    }
    
    // files not existing should fail as expected
    match get_file_path("/safedir/noexist.txt") {
        Ok(_file) => {
            // File automatically closes when it goes out of scope
        }
        Err(FileError::Io(io_err)) if io_err.kind() == std::io::ErrorKind::NotFound => {
            println!("File not found as expected");
        }
        Err(e) => {
            println!("Unexpected error: {:?}", e);
        }
    }
    
    // files outside of not allowed path should raise PathNotAllowed exception
    match get_file_path("not/a/good/path.txt") {
        Ok(_file) => {
            // File automatically closes when it goes out of scope
        }
        Err(FileError::PathNotAllowed(_)) => {
            println!("Path not allowed as expected");
        }
        Err(e) => {
            println!("Unexpected error: {:?}", e);
        }
    }
}