use std::fs::File;
use std::io::{self, Read};

fn get_file(path: &str) -> Result<File, String> {
    if !path.starts_with("/safedir") {
        return Err("Access denied".to_string());
    }
    File::open(path).map_err(|e| e.to_string())
}

fn main() {
    match get_file("/safedir/test1.txt") {
        Ok(mut file) => {
            let mut contents = String::new();
            if let Err(e) = file.read_to_string(&mut contents) {
                eprintln!("Error reading file: {}", e);
            } else {
                println!("{}", contents);
            }
        },
        Err(e) => eprintln!("Error: {}", e),
    }

    // The following line is commented out, similar to the Python code
    // match get_file("/etc/passwd") {
    //     Ok(mut file) => {
    //         let mut contents = String::new();
    //         if let Err(e) = file.read_to_string(&mut contents) {
    //             eprintln!("Error reading file: {}", e);
    //         } else {
    //             println!("{}", contents);
    //         }
    //     },
    //     Err(e) => eprintln!("Error: {}", e),
    // }
}