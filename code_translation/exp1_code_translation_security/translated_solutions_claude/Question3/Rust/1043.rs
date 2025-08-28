use std::fs;
use std::path::Path;
use regex::Regex;

/// Search file in a path
fn search_file_re(file_pattern: &str) -> Option<String> {
    // Compile the regex pattern
    let re = match Regex::new(file_pattern) {
        Ok(regex) => regex,
        Err(_) => return None,
    };
    
    // Walk through the directory recursively
    fn walk_dir(dir: &Path, re: &Regex) -> Option<String> {
        if let Ok(entries) = fs::read_dir(dir) {
            for entry in entries.flatten() {
                let path = entry.path();
                
                if path.is_dir() {
                    // Recursively search in subdirectories
                    if let Some(result) = walk_dir(&path, re) {
                        return Some(result);
                    }
                } else if path.is_file() {
                    // Check if filename matches the pattern
                    if let Some(filename) = path.file_name() {
                        if let Some(filename_str) = filename.to_str() {
                            if re.is_match(filename_str) {
                                return Some(path.to_string_lossy().to_string());
                            }
                        }
                    }
                }
            }
        }
        None
    }
    
    let safedir = Path::new("/safedir");
    walk_dir(safedir, &re)
}

/// Main function
fn main() {
    let file_path = search_file_re("test12.txt");
    match file_path {
        Some(path) => println!("{}", path),
        None => println!("None"),
    }
}