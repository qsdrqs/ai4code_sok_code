use std::fs::File;
use std::io::Read;

fn func_path(path: &str) -> String {
    if path.contains("/safedir") {
        match File::open(path) {
            Ok(mut file) => {
                let mut contents = String::new();
                match file.read_to_string(&mut contents) {
                    Ok(_) => contents,
                    Err(e) => format!("Error reading file: {}", e),
                }
            }
            Err(e) => format!("Error opening file: {}", e),
        }
    } else {
        String::from("file not found or unsafe for access.")
    }
}

fn main() {
    println!("{}", func_path("/safedir/Jim"));
}