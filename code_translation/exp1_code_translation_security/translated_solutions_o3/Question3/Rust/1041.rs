use std::fs::File;
use std::io::{self, Error, ErrorKind};

/// Opens a file only if its path starts with `/safedir`.
fn open_file(path: &str) -> io::Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(Error::new(
            ErrorKind::InvalidInput,
            "Path must start with /safedir",
        ))
    }
}

fn main() {
    let res1 = open_file("/safedir/random.txt");
    println!("{:?}", res1); // Prints Ok(File) or an error if the file doesn't exist.

    let res2 = open_file("blah");
    println!("{:?}", res2); // Always prints an error due to the prefix check.
}