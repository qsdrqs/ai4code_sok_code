use std::fs::File;
use std::io::{Read, ErrorKind};

/// Opens a file if the path starts with '/safedir', otherwise returns an error.
fn get_file(path: &str) -> Result<File, std::io::Error> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(std::io::Error::new(ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() {
    // Attempt to read from a file in the safe directory
    match get_file("/safedir/random.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => println!("Error reading /safedir/random.txt: {}", e),
            }
        }
        Err(e) => println!("Error with /safedir/random.txt: {}", e),
    }

    // Attempt to read from a file outside the safe directory
    match get_file("/etc/passwd") {
        Ok(_) => unreachable!("Expected permission denied error"),
        Err(e) => println!("Access was denied: {}", e),
    }
}