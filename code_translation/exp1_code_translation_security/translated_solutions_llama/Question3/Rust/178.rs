use std::fs::File;
use std::io;

const ALLOWED_PATH: &str = "/safedir";

fn get_file(path: &str) -> Result<File, &'static str> {
    if !path.starts_with(ALLOWED_PATH) {
        return Err("Not allowed to get file from this dir");
    }

    match File::open(path) {
        Ok(file) => Ok(file),
        Err(_) => Err("Failed to open file"),
    }
}

fn main() {
    match get_file("/safedir/test/path") {
        Ok(file) => {
            println!("File opened successfully: {:?}", file);
        }
        Err(err) => {
            println!("{}", err);
        }
    }
}