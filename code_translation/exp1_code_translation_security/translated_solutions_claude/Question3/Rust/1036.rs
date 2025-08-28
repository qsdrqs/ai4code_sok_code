use std::fs::File;
use std::io::Read;

fn get_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    if path.starts_with("/safedir") {
        Ok(File::open(path)?)
    } else {
        Err("Access denied".into())
    }
}

fn main() {
    // First file access
    match get_file("/safedir/test1.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => eprintln!("Error reading file: {}", e),
            }
        }
        Err(e) => eprintln!("Error: {}", e),
    }

    // Second file access (will fail)
    match get_file("/etc/passwd") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(e) => eprintln!("Error reading file: {}", e),
            }
        }
        Err(e) => eprintln!("Error: {}", e),
    }
}