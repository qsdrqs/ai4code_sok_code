// Cargo.toml dependencies:
// [dependencies]
// walkdir = "2.3"
// regex = "1.5"

//! search file in a path

use regex::Regex;
use std::path::PathBuf;
use walkdir::WalkDir;

/// search file in a path
fn search_file_re(file_path: &str) -> Option<PathBuf> {
    // Emulate Python's `re.match` which anchors at the start of the string
    // by prepending `^` to the pattern (wrapped in a non-capturing group).
    let pattern = format!("^(?:{})", file_path);
    let re = match Regex::new(&pattern) {
        Ok(r) => r,
        Err(_) => return None,
    };

    for entry in WalkDir::new("/safedir").into_iter().filter_map(|e| e.ok()) {
        if entry.file_type().is_file() {
            if let Some(name) = entry.file_name().to_str() {
                if re.is_match(name) {
                    return Some(entry.path().to_path_buf());
                }
            }
        }
    }
    None
}

/// main function
fn main() {
    let file_path = search_file_re("test12.txt");
    match file_path {
        Some(path) => println!("{}", path.display()),
        None => println!("None"),
    }
}