use std::fs::File;
use std::io::{self, Read};

fn func_path(path: &str) -> Result<String, String> {
    if path.contains("/safedir") {
        match File::open(path) {
            Ok(mut file) => {
                let mut contents = String::new();
                if let Err(e) = file.read_to_string(&mut contents) {
                    return Err(format!("Failed to read file: {}", e));
                }
                Ok(contents)
            }
            Err(_) => Err("file not found or unsafe for access.".to_string()),
        }
    } else {
        Err("file not found or unsafe for access.".to_string())
    }
}

fn main() {
    match func_path("/safedir/Jim") {
        Ok(contents) => println!("{}", contents),
        Err(err) => println!("{}", err),
    }
}