use std::error::Error;
use std::fmt;
use std::fs::File;
use std::io;

#[derive(Debug)]
enum FileError {
    PathNotAllowed(String),
    IoError(io::Error),
}

impl fmt::Display for FileError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            FileError::PathNotAllowed(msg) => write!(f, "PathNotAllowed: {}", msg),
            FileError::IoError(err) => write!(f, "IoError: {}", err),
        }
    }
}

impl Error for FileError {
    fn source(&self) -> Option<&(dyn Error + 'static)> {
        match self {
            FileError::PathNotAllowed(_) => None,
            FileError::IoError(err) => Some(err),
        }
    }
}

impl From<io::Error> for FileError {
    fn from(err: io::Error) -> FileError {
        FileError::IoError(err)
    }
}

fn get_file_path(path: &str) -> Result<File, FileError> {
    if !path.starts_with("/safedir") {
        return Err(FileError::PathNotAllowed(
            "filepath not allowed, only /safedir/... file paths are accessible".to_string(),
        ));
    }

    Ok(File::open(path)?)
}

fn main() {
    let prefix = "/safedir/";

    let good_paths = [
        format!("{}test1.txt", prefix),
        format!("{}test.txt", prefix),
    ];

    // should open without exception
    for path in &good_paths {
        let _f = get_file_path(path).expect("Failed to open file");
        // File closed automatically when _f goes out of scope (Drop)
    }

    // files not existing should fail as expected
    match get_file_path("/safedir/noexist.txt") {
        Ok(_) => {}
        Err(FileError::IoError(ref e)) if e.kind() == io::ErrorKind::NotFound => {
            // Expected error, ignore
        }
        Err(e) => panic!("Unexpected error: {}", e),
    }

    // files outside of not allowed path should raise PathNotAllowed exception
    match get_file_path("not/a/good/path.txt") {
        Ok(_) => {}
        Err(FileError::PathNotAllowed(_)) => {
            // Expected error, ignore
        }
        Err(e) => panic!("Unexpected error: {}", e),
    }
}