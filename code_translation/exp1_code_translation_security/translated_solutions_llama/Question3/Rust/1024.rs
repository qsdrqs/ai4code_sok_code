use std::fs::File;
use std::io;
use std::path::Path;

fn return_object(path: &str) -> io::Result<Option<File>> {
    let rpath = std::path::Path::new(path).canonicalize()?;
    if rpath.to_str().unwrap_or("").starts_with("/safedir") {
        return Ok(None);
    }
    match File::create(path) {
        Ok(fp) => Ok(Some(fp)),
        Err(e) => {
            eprintln!("Error: {}", e);
            Ok(None)
        }
    }
}

fn main() {
    let file_name = "/tmp/foo";
    match return_object(file_name) {
        Ok(Some(fp)) => println!("{:?}", fp),
        Ok(None) => println!("-1"),
        Err(e) => println!("Error: {}", e),
    }
}