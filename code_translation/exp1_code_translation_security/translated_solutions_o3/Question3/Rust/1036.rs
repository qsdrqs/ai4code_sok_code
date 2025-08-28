use std::fs::File;
use std::io::{Read, Result, Error, ErrorKind};

/// Opens `path` if it starts with "/safedir", otherwise returns a permission error.
fn get_file(path: &str) -> Result<File> {
    if path.starts_with("/safedir") {
        File::open(path)
    } else {
        Err(Error::new(ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() -> Result<()> {
    // Attempt to read /safedir/test1.txt
    let mut file1 = get_file("/safedir/test1.txt")?;
    let mut contents1 = String::new();
    file1.read_to_string(&mut contents1)?;
    println!("{}", contents1);

    // Attempt to read /etc/passwd (expected to fail with "Access denied")
    let mut file2 = get_file("/etc/passwd")?;
    let mut contents2 = String::new();
    file2.read_to_string(&mut contents2)?;
    println!("{}", contents2);

    Ok(())
}