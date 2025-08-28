use std::fs::File;
use std::io::BufReader;
use std::path::{Path, PathBuf};

pub fn open_file(path: &str) -> Option<File> {
    // Helper function to emulate os.path.normcase
    fn normcase(mut s: String) -> String {
        if cfg!(windows) {
            s = s.to_lowercase();
            s.replace("/", "\\")
        } else {
            s
        }
    }

    // Helper function to compute the common prefix of two paths
    fn common_prefix(a: &Path, b: &Path) -> Option<PathBuf> {
        let a_components: Vec<_> = a.components().collect();
        let b_components: Vec<_> = b.components().collect();

        let min_len = std::cmp::min(a_components.len(), b_components.len());
        let mut i = 0;

        while i < min_len && a_components[i] == b_components[i] {
            i += 1;
        }

        if i == 0 {
            None
        } else {
            Some(a_components[..i].iter().collect())
        }
    }

    // Inner function to check if the file is in the safe directory
    fn in_safe(fname: &str) -> bool {
        // Canonicalize the file path to resolve symlinks
        let canonical_fname = match std::fs::canonicalize(fname) {
            Ok(path) => path,
            Err(_) => return false,
        };

        // Get the parent directory of the canonical path
        let fname_dir = match canonical_fname.parent() {
            Some(dir) => dir.to_path_buf(),
            None => return false,
        };

        // Convert fname_dir to a string and apply normcase
        let fname_dir_str = match fname_dir.to_str() {
            Some(s) => s.to_string(),
            None => return false, // Invalid UTF-8
        };
        let fname_dir_norm_str = normcase(fname_dir_str);
        let fname_dir_norm = PathBuf::from(fname_dir_norm_str);

        // Safe directory path
        let safe_dir_str = "/safedir";
        let safe_dir_norm_str = normcase(safe_dir_str.to_string());
        let safe_dir_norm = PathBuf::from(safe_dir_norm_str);

        // Compute common prefix
        let common = common_prefix(&fname_dir_norm, &safe_dir_norm);

        match common {
            Some(p) => p == safe_dir_norm,
            None => false,
        }
    }

    // Main logic: open the file if it's in the safe directory
    if in_safe(path) {
        match File::open(path) {
            Ok(file) => Some(file),
            Err(_) => None,
        }
    } else {
        None
    }
}

fn main() {
    // Example usage
    let file = open_file("/env.sh");
    println!("{:?}", file);
}