use std::error::Error;
use std::fs::File;
use std::io::{Read, Result as IoResult};

/// Returns an opened file handle only if the path begins with "/safedir".
fn get_file(path: &str) -> Result<File, Box<dyn Error>> {
    if path.starts_with("/safedir") {
        // Propagate any I/O error that might occur while opening the file.
        Ok(File::open(path)?)
    } else {
        Err("Access denied".into())
    }
}

fn read_file_to_string(mut file: File) -> IoResult<String> {
    let mut buf = String::new();
    file.read_to_string(&mut buf)?;
    Ok(buf)
}

fn main() {
    // First attempt: "/safedir/random.txt"
    match get_file("/safedir/random.txt")
        .and_then(read_file_to_string)
    {
        Ok(contents) => println!("{contents}"),
        Err(_)       => println!("Error with /safedir/random.txt"),
    }

    // Second attempt: "/etc/passwd"
    match get_file("/etc/passwd")
        .and_then(read_file_to_string)
    {
        Ok(contents) => println!("{contents}"),
        Err(_)       => println!("Access was denied"),
    }
}