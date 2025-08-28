use std::fs::File;
use std::io;
use std::path::Path;

#[derive(Debug)]
struct PathNotAllowed;

impl std::fmt::Display for PathNotAllowed {
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
        write!(f, "filepath not allowed, only /safedir/... file paths are accessible")
    }
}

impl std::error::Error for PathNotAllowed {}

fn get_file_path(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if !path.starts_with("/safedir") {
        return Err(Box::new(PathNotAllowed));
    }
    let file = File::open(path)?;
    Ok(file)
}

fn main() -> io::Result<()> {
    let prefix = "/safedir/";

    let good_paths = [
        format!("{}test1.txt", prefix),
        format!("{}test.txt", prefix),
    ];

    // should open without exception
    for path in &good_paths {
        match get_file_path(path) {
            Ok(mut file) => {
                // Do something with the file if needed
                file.sync_all()?;
            }
            Err(e) => eprintln!("Error opening file {}: {}", path, e),
        }
    }

    // files not existing should fail as expected
    match get_file_path("/safedir/noexist.txt") {
        Ok(mut file) => {
            // Do something with the file if needed
            file.sync_all()?;
        }
        Err(e) => eprintln!("Expected error for non-existing file: {}", e),
    }

    // files outside of not allowed path should raise PathNotAllowed exception
    match get_file_path("not/a/good/path.txt") {
        Ok(mut file) => {
            // Do something with the file if needed
            file.sync_all()?;
        }
        Err(e) => eprintln!("Expected PathNotAllowed error: {}", e),
    }

    Ok(())
}