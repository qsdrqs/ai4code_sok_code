use std::fs::File;
use std::io::{Read, Error};
use std::path::Path;

fn get_file(path: &str) -> Result<File, Error> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err(Error::new(std::io::ErrorKind::PermissionDenied, "Access denied"))
    }
}

fn main() -> std::io::Result<()> {
    let safe_file = get_file("/safedir/test1.txt")?;
    let mut safe_file_content = String::new();
    safe_file.read_to_string(&mut safe_file_content)?;
    println!("{}", safe_file_content);

    let unsafe_file_result = get_file("/etc/passwd");
    match unsafe_file_result {
        Ok(file) => {
            let mut unsafe_file_content = String::new();
            file.read_to_string(&mut unsafe_file_content)?;
            println!("{}", unsafe_file_content);
        },
        Err(e) => {
            eprintln!("Error: {}", e);
        }
    }

    Ok(())
}