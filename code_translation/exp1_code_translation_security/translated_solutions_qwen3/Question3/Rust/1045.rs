extern crate path_clean;

use path_clean::PathClean;
use std::fs::File;
use std::path::{Path, PathBuf};
use std::env;

/// Returns the absolute path of the given path, normalized to collapse `.` and `..`.
fn abspath(path: &str) -> PathBuf {
    let mut path_buf = PathBuf::from(path);
    if path_buf.is_absolute() {
        path_buf.clean()
    } else {
        let current_dir = env::current_dir()
            .expect("Failed to get current working directory");
        current_dir.join(path_buf).clean()
    }
}

/// Opens a file if its absolute path starts with `/safedir`.
fn open_file(path: &str) -> Result<File, String> {
    let safe_dir = Path::new("/safedir").clean();
    let absolute_path = abspath(path);

    if absolute_path.starts_with(&safe_dir) {
        File::open(path).map_err(|e| format!("IO error: {}", e))
    } else {
        Err("Access Denied".to_string())
    }
}

fn main() {
    match open_file("/safedir/test.txt") {
        Ok(file) => println!("File opened successfully: {:?}", file),
        Err(e) => println!("Error: {}", e),
    }
}