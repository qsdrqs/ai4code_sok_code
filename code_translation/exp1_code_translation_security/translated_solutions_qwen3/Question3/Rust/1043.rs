// Import necessary external crates
extern crate walkdir;
extern crate regex;

// Use modules from the crates
use walkdir::WalkDir;
use regex::Regex;
use std::path::Path;

/// Searches for a file in the `/safedir` directory whose name matches the given regex pattern.
/// Returns the full path of the first matching file, or `None` if no match is found.
fn search_file_re(file_path: &str) -> Option<String> {
    // Compile the regex pattern from the input string
    let regex = Regex::new(file_path).expect("Invalid regex pattern");

    // Traverse the directory tree recursively
    for entry in WalkDir::new("/safedir").into_iter().filter_map(|e| e.ok()) {
        // Only consider files (not directories or other types)
        if entry.file_type().is_file() {
            // Convert the file name to a UTF-8 string (panics if not valid UTF-8)
            let name = entry.file_name().to_str().unwrap();

            // Check if the file name matches the regex
            if regex.is_match(name) {
                // Return the full path of the matching file
                return Some(entry.path().display().to_string());
            }
        }
    }

    // No match found
    None
}

/// Main function to demonstrate the file search functionality.
fn main() {
    // Search for a file matching the regex "test12.txt"
    let file_path = search_file_re("test12.txt");

    // Print the result (Some(path) or None)
    println!("{:?}", file_path);
}