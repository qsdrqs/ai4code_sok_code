use std::env;
use std::fs;
use std::path::Path;
use regex::Regex;

fn search_file_re(file_path: &str, dir_path: &str) -> Option<String> {
    let re = Regex::new(file_path).unwrap();
    for entry in fs::read_dir(dir_path).unwrap() {
        let entry = entry.unwrap();
        let path = entry.path();
        if path.is_file() {
            if re.is_match(path.file_name().unwrap().to_str().unwrap()) {
                return Some(path.to_str().unwrap().to_string());
            }
        } else if path.is_dir() {
            if let Some(file_path) = search_file_re(file_path, path.to_str().unwrap()) {
                return Some(file_path);
            }
        }
    }
    None
}

fn main() {
    let file_path = "test12.txt";
    let dir_path = "/safedir";
    match search_file_re(file_path, dir_path) {
        Some(path) => println!("{}", path),
        None => println!("File not found"),
    }
}