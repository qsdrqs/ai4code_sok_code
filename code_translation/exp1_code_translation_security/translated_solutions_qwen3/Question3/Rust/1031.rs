use std::path::Path;
use std::fs::File;

fn find_path(path: &str) -> Option<File> {
    let safe_path = Path::new("/safedir");
    let dir = Path::new(path).parent().unwrap_or_else(|| Path::new(""));

    if dir == safe_path {
        let file = File::open(path).expect("Failed to open file");
        println!("safe!");
        Some(file)
    } else {
        println!("not safe!");
        None
    }
}

fn main() {
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
}