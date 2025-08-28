use std::fs::File;
use std::io::{self, Read};

const ALLOWED_PATH: &str = "/safedir";

fn get_file(path: &str) -> Result<String, String> {
    if !path.starts_with(ALLOWED_PATH) {
        return Err(String::from("Not allowed to get file from this dir"));
    }

    let mut file = File::open(path).map_err(|_| String::from("Failed to open file"))?;
    let mut contents = String::new();
    file.read_to_string(&mut contents).map_err(|_| String::from("Failed to read file"))?;
    Ok(contents)
}

fn main() {
    match get_file("/safedir/test/path") {
        Ok(contents) => println!("File contents: {}", contents),
        Err(err) => println!("Error: {}", err),
    }
}