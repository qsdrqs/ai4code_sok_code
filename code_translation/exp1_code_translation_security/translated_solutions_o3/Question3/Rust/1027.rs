use std::fs::File;
use std::io;

fn open_file(file_path: &str) -> Option<File> {
    // Make sure the file is inside "/safedir".
    if !file_path.starts_with("/safedir") {
        panic!("File is outside of /safedir");
    }

    // Attempt to open the file in read-only mode.
    match File::open(file_path) {
        Ok(file) => Some(file),
        Err(err) => {
            // In Python we only printed “File not found” for an IOError.
            // Here we mirror that for “NotFound”; everything else we also print
            // but still return `None`, just like the Python code falls through.
            if err.kind() == io::ErrorKind::NotFound {
                println!("File not found");
            } else {
                println!("Failed to open file: {}", err);
            }
            None
        }
    }
}

fn main() {
    let result = open_file("/safedir/Jim");

    // Because `std::fs::File` doesn’t implement `Debug`, we can’t just
    // `println!("{:?}", result)`.  The following prints `Some("file handle")`
    // if it succeeded, or `None` otherwise, roughly matching Python’s
    // “print whatever object we got back”.
    println!("{:?}", result.as_ref().map(|_| "file handle"));
}