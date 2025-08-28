use std::fs::File;
use std::io;
use std::path::Path;

// Custom error type for paths not allowed
#[derive(Debug)]
struct PathNotAllowed(String);

impl std::error::Error for PathNotAllowed {}

impl std::fmt::Display for PathNotAllowed {
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        write!(f, "{}", self.0)
    }
}

// Function to get a file path, ensuring it starts with the safe directory prefix
fn get_file_path(path: &str) -> Result<File, io::Error> {
    const SAFE_DIR_PREFIX: &str = "/safedir/";
    if !path.starts_with(SAFE_DIR_PREFIX) {
        return Err(io::Error::new(
            io::ErrorKind::PermissionDenied,
            PathNotAllowed(format!("filepath not allowed, only {}... file paths are accessible", SAFE_DIR_PREFIX)),
        ));
    }
    File::open(path)
}

fn main() {
    const PREFIX: &str = "/safedir/";

    let good_paths = [
        format!("{}{}", PREFIX, "test1.txt"),
        format!("{}{}", PREFIX, "test.txt"),
    ];

    // Should open without exception
    for path in &good_paths {
        match get_file_path(path) {
            Ok(file) => {
                drop(file); // File is automatically closed when it goes out of scope
            }
            Err(e) => {
                eprintln!("Error opening {}: {}", path, e);
            }
        }
    }

    // Files not existing should fail as expected
    match get_file_path("/safedir/noexist.txt") {
        Ok(_) => unreachable!("File should not exist"),
        Err(e) => {
            eprintln!("Error opening /safedir/noexist.txt: {}", e);
            if e.kind() == io::ErrorKind::NotFound {
                println!("File not found, as expected");
            }
        }
    }

    // Files outside of not allowed path should raise PathNotAllowed exception
    match get_file_path("not/a/good/path.txt") {
        Ok(_) => unreachable!("Path should not be allowed"),
        Err(e) => {
            eprintln!("Error opening not/a/good/path.txt: {}", e);
            if let Some(custom_err) = e.get_ref().and_then(|inner| inner.downcast_ref::<PathNotAllowed>()) {
                println!("PathNotAllowed exception caught: {}", custom_err);
            }
        }
    }
}