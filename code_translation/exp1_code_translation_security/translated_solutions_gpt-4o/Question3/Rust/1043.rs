use std::env;
use std::fs;
use std::path::Path;
use regex::Regex;

/// Search for a file in a given path
fn search_file_re(file_pattern: &str) -> Option<String> {
    // Define the base directory to search in
    let base_dir = "/safedir";

    // Compile the regex pattern
    let re = Regex::new(file_pattern).expect("Invalid regex pattern");

    // Walk through the directory
    if let Ok(entries) = fs::read_dir(base_dir) {
        for entry in entries {
            if let Ok(entry) = entry {
                let path = entry.path();
                if path.is_file() {
                    if let Some(file_name) = path.file_name() {
                        if let Some(file_name_str) = file_name.to_str() {
                            if re.is_match(file_name_str) {
                                return Some(path.to_string_lossy().to_string());
                            }
                        }
                    }
                }
            }
        }
    }

    None
}

/// Main function
fn main() {
    // Search for the file
    let file_path = search_file_re("test12\\.txt");

    // Print the result
    match file_path {
        Some(path) => println!("{}", path),
        None => println!("File not found"),
    }
}