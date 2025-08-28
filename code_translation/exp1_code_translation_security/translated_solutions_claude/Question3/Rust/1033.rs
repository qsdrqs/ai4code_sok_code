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
    // Try to read /safedir/random.txt
    match get_file("/safedir/random.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(_) => println!("Error with /safedir/random.txt"),
            }
        }
        Err(_) => println!("Error with /safedir/random.txt"),
    }

    // Try to read /etc/passwd
    match get_file("/etc/passwd") {
        Ok(mut file) => {
            let mut contents = String::new();
            match file.read_to_string(&mut contents) {
                Ok(_) => println!("{}", contents),
                Err(_) => println!("Access was denied"),
            }
        }
        Err(_) => println!("Access was denied"),
    }
}