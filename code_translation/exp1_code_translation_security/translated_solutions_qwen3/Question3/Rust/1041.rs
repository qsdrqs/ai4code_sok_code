// Import necessary modules from the standard library
use std::fs::File;
use std::io::{self, ErrorKind};

/// Opens a file if the path starts with "/safedir".
/// Returns a `Result<File, io::Error>`:
/// - `Ok(File)` if the path is valid and the file is opened successfully.
/// - `Err(io::Error)` if the path is invalid or the file cannot be opened.
fn open_file(path: &str) -> Result<File, io::Error> {
    if path.starts_with("/safedir") {
        // Attempt to open the file using the standard library
        File::open(path)
    } else {
        // Return a custom error if the path does not start with "/safedir"
        Err(io::Error::new(
            ErrorKind::InvalidInput,
            "Path must start with /safedir",
        ))
    }
}

fn main() {
    // First call: valid path
    let res1 = open_file("/safedir/random.txt").unwrap();
    println!("{:?}", res1); // Prints the file object (e.g., File { fd: 3, ... })

    // Second call: invalid path
    let res2 = open_file("blah").unwrap(); // This will panic due to unwrap()
}