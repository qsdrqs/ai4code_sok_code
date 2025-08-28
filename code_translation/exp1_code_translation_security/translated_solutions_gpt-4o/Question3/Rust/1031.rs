use std::fs::File;
use std::path::Path;

fn find_path(path: &str) {
    let safe_path = "/safedir";
    let dirname = Path::new(path).parent().unwrap_or_else(|| Path::new(""));

    if dirname == Path::new(safe_path) {
        match File::open(path) {
            Ok(_) => println!("safe!"),
            Err(e) => println!("Failed to open file: {}", e),
        }
    } else {
        println!("not safe!");
    }
}

fn main() {
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
}