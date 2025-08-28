use std::fs::File;
use std::io::Error as IoError;

fn open_file(file_path: &str) -> Result<Option<File>, Box<dyn std::error::Error>> {
    if !file_path.starts_with("/safedir") {
        return Err("File is outside of /safedir".into());
    }
    
    match File::open(file_path) {
        Ok(file_obj) => Ok(Some(file_obj)),
        Err(_) => {
            println!("File not found");
            Ok(None)
        }
    }
}

fn main() {
    match open_file("/safedir/Jim") {
        Ok(path) => println!("{:?}", path),
        Err(e) => println!("Error: {}", e),
    }
}