use std::fs::File;
use std::io::Result;

fn get_file_object(input_path: &str) -> Option<Result<File>> {
    if input_path.len() >= 9 {
        let first_part = &input_path[0..9];
        if first_part == "/safedir/" {
            return Some(File::open(input_path));
        }
    }
    None
}

fn main() {
    if let Some(file_result) = get_file_object("/safedir/test.txt") {
        match file_result {
            Ok(_file) => println!("File opened successfully"),
            Err(e) => println!("Error opening file: {}", e),
        }
    } else {
        println!("Invalid path");
    }
}