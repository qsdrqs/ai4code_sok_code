use std::path::Path;
use std::fs::File;
use std::io::Result;

fn find_path(path: &str) -> Option<File> {
    let safe_path = "/safedir";
    let path_obj = Path::new(path);
    
    if let Some(dirname) = path_obj.parent() {
        if dirname.to_str() == Some(safe_path) {
            match File::open(path) {
                Ok(file) => {
                    println!("safe!");
                    return Some(file);
                }
                Err(_) => {
                    println!("safe!");
                    return None;
                }
            }
        } else {
            println!("not safe!");
            return None;
        }
    } else {
        println!("not safe!");
        return None;
    }
}

fn main() {
    find_path("/safedir/random.txt");
    find_path("/home/codex/Documents/User_Study_UI");
}