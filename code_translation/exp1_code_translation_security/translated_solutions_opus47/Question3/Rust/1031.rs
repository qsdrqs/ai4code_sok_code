use std::path::Path;
use std::fs::File;

fn find_path(path: &str) -> Option<File> {
    let safe_path = "/safedir";
    let dirname = Path::new(path).parent();
    
    if dirname == Some(Path::new(safe_path)) {
        let file = File::open(path).unwrap();
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