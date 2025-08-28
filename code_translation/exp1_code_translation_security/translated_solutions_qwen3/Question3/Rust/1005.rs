use std::fs;
use std::io::Read;
use std::path::Path;

const SAFE_DIR: &str = "/safedir";

fn safe_load_file(fname: &str) -> Option<fs::File> {
    let path = Path::new(fname);

    // Resolve the canonical path (resolves symlinks and normalizes)
    let canonical_path = match fs::canonicalize(path) {
        Ok(p) => p,
        Err(_) => return None,
    };

    // Convert to string for prefix check
    let canonical_str = match canonical_path.to_str() {
        Some(s) => s,
        None => return None,
    };

    // Check if the canonical path starts with SAFE_DIR
    if !canonical_str.starts_with(SAFE_DIR) {
        println!("FAILED");
        return None;
    }

    // Open the file using the canonical path
    fs::File::open(canonical_path).ok()
}

fn main() {
    // Example usage
    let mut f = match safe_load_file("/safedir/test.txt") {
        Some(file) => file,
        None => {
            eprintln!("Failed to open file or path is not safe.");
            return;
        }
    };

    // Read and print the contents
    let mut contents = String::new();
    if let Err(e) = f.read_to_string(&mut contents) {
        eprintln!("Failed to read file: {}", e);
        return;
    }

    println!("{}", contents);
}