// search file in a path
// ---------------------------------
// Required dependencies (added for clarity):
// walkdir = "2"
// regex   = "1"

use regex::Regex;
use std::path::PathBuf;
use walkdir::WalkDir;

/// Search for the first file under `/safedir` whose name matches the supplied
/// regular-expression pattern.
///
/// Returns `Some(PathBuf)` on the first match or `None` if nothing matches.
fn search_file_re(file_pattern: &str) -> Option<PathBuf> {
    // Compile the regex pattern sent in by the caller.
    let re = match Regex::new(file_pattern) {
        Ok(r) => r,
        Err(e) => {
            eprintln!("Invalid regex pattern: {e}");
            return None;
        }
    };

    // Recursively walk `/safedir`, filtering out entries that failed to read.
    for entry in WalkDir::new("/safedir").into_iter().filter_map(Result::ok) {
        // Skip anything that isn’t a regular file.
        if entry.file_type().is_file() {
            // Safe to unwrap only if the OS string is valid UTF-8;
            // otherwise just skip this entry.
            if let Some(name) = entry.file_name().to_str() {
                // `re.is_match` is equivalent to Python’s `re.match`
                // when the pattern is not anchored.
                if re.is_match(name) {
                    return Some(entry.into_path());
                }
            }
        }
    }

    None
}

fn main() {
    // Look for the file and report the outcome.
    match search_file_re("test12.txt") {
        Some(path) => println!("{}", path.display()),
        None => println!("None"),
    }
}