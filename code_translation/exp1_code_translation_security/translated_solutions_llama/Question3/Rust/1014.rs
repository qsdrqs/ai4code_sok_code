use std::fs::File;
use std::io::{Read, Error};
use std::path::Path;

// Function to open a file if the path starts with "/safedir"
fn open_file(path: &str) -> Result<File, Error> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(Error::new(std::io::ErrorKind::PermissionDenied, "Access denied"))
    }
}

// Main function
fn main() -> std::io::Result<()> {
    let path = "/safedir/test1.txt";
    let mut file = open_file(path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    println!("{}", contents);
    Ok(())
}