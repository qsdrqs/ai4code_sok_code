// To run this code, you need to add the following dependencies to your Cargo.toml file:
//
// [dependencies]
// walkdir = "2"
// regex = "1"

use regex::Regex;
use std::path::PathBuf;
use walkdir::WalkDir;

/// Searches for a file in a directory tree based on a regex pattern.
///
/// This function corresponds to the Python `search_file_re`.
/// It searches for a file in the `/safedir` directory tree whose name
/// matches the given regex pattern from the beginning of the string.
fn search_file_re(file_pattern: &str) -> Option<PathBuf> {
    // Python's `re.match` checks for a match only at the beginning of the string.
    // To replicate this behavior, we anchor the regex pattern to the start with `^`.
    let anchored_pattern = format!("^{}", file_pattern);
    let re = match Regex::new(&anchored_pattern) {
        Ok(re) => re,
        Err(e) => {
            // In case of an invalid regex pattern, print an error and return None.
            eprintln!("Invalid regex pattern: {}", e);
            return None;
        }
    };

    // `os.walk` equivalent using the `walkdir` crate.
    // We iterate through all entries, skipping any that produce errors (e.g., permission denied).
    for entry in WalkDir::new("/safedir").into_iter().filter_map(|e| e.ok()) {
        // We are only interested in files, not directories.
        if entry.file_type().is_file() {
            // Get the file name. `file_name()` returns an `OsStr`. We convert it to a `&str`.
            if let Some(name) = entry.file_name().to_str() {
                // Check if the file name matches the regex.
                if re.is_match(name) {
                    // `os.path.join` is handled by `walkdir` which gives the full path.
                    // We found a match, so we return the path and stop searching.
                    return Some(entry.path().to_path_buf());
                }
            }
        }
    }

    // If the loop completes without finding a match, return None.
    None
}

/// The main function, which serves as the entry point for the program.
fn main() {
    // Call the search function.
    let file_path_option = search_file_re("test12.txt");

    // Print the result.
    // The Python code prints the path if found, or the string "None" if not.
    // We use a `match` statement to achieve the same behavior in Rust.
    match file_path_option {
        Some(path) => {
            // `path.display()` is the idiomatic way to print a path.
            println!("{}", path.display());
        }
        None => {
            println!("None");
        }
    }
}