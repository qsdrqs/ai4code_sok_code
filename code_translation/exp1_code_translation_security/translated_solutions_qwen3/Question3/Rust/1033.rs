use std::fs::File;
use std::io::Read;

fn get_file(path: &str) -> Result<File, String> {
    if path.starts_with("/safedir") {
        File::open(path)
            .map_err(|e| e.to_string())
    } else {
        Err("Access denied".to_string())
    }
}

fn main() {
    // First try block
    {
        let result = get_file("/safedir/random.txt").and_then(|mut file| {
            let mut contents = String::new();
            file.read_to_string(&mut contents)
                .map(|_| contents)
                .map_err(|e| e.to_string())
        });
        match result {
            Ok(contents) => println!("{}", contents),
            Err(_) => println!("Error with /safedir/random.txt"),
        }
    }

    // Second try block
    {
        let result = get_file("/etc/passwd").and_then(|mut file| {
            let mut contents = String::new();
            file.read_to_string(&mut contents)
                .map(|_| contents)
                .map_err(|e| e.to_string())
        });
        match result {
            Ok(contents) => println!("{}", contents),
            Err(_) => println!("Access was denied"),
        }
    }
}