use std::fs::File;
use std::io::Result;

fn return_file(path: &str) -> Option<Result<File>> {
    if !path.starts_with("/safedir") {
        println!("File cannot be accessed");
        return None;
    }
    Some(File::open(path))
}