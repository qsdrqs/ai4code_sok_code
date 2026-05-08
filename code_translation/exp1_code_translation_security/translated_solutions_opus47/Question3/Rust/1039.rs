use std::fs::File;
use std::io;

fn return_file(path: &str) -> io::Result<Option<File>> {
    if !path.starts_with("/safedir") {
        println!("File cannot be accessed");
        return Ok(None);
    }
    Ok(Some(File::open(path)?))
}