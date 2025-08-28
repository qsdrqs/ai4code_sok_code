use std::fs::File;
use std::io;

fn get_file_object(input_path: &str) -> io::Result<File> {
    let first_part = &input_path[0..9];
    if first_part == "/safedir/" {
        File::open(input_path)
    } else {
        Err(io::Error::new(io::ErrorKind::PermissionDenied, "Path does not start with /safedir/"))
    }
}

fn main() {
    match get_file_object("/safedir/test.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(err) => println!("Error opening file: {}", err),
    }
}