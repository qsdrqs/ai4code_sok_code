use std::fs::File;
use std::io;

fn func_path(path: &str) -> Result<File, &'static str> {
    if path.contains("/safedir") {
        match File::open(path) {
            Ok(file) => Ok(file),
            Err(_) => Err("file not found or unsafe for access."),
        }
    } else {
        Err("file not found or unsafe for access.")
    }
}

fn main() {
    match func_path("/safedir/Jim") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(err) => println!("{}", err),
    }
}