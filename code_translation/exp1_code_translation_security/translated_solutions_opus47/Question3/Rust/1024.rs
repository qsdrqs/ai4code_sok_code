use std::fs::File;
use std::path::{Path, PathBuf};
use std::fmt;

// Enum to represent the three possible return types from the Python function:
// -1 (int), a file handle, or None
enum ReturnType {
    ErrorCode(i32),
    FileHandle(File),
    Nothing,
}

impl fmt::Display for ReturnType {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            ReturnType::ErrorCode(code) => write!(f, "{}", code),
            ReturnType::FileHandle(file) => write!(f, "{:?}", file),
            ReturnType::Nothing => write!(f, "None"),
        }
    }
}

// Equivalent of os.path.realpath - resolves symlinks and normalizes path
// Falls back gracefully if the file doesn't exist yet
fn realpath(path: &str) -> PathBuf {
    let p = Path::new(path);
    match std::fs::canonicalize(p) {
        Ok(abs) => abs,
        Err(_) => {
            // If path doesn't exist, try to canonicalize the parent directory
            if let Some(parent) = p.parent() {
                if !parent.as_os_str().is_empty() {
                    if let Ok(canonical_parent) = std::fs::canonicalize(parent) {
                        if let Some(file_name) = p.file_name() {
                            return canonical_parent.join(file_name);
                        }
                    }
                }
            }
            p.to_path_buf()
        }
    }
}

fn return_object(path: &str) -> ReturnType {
    let rpath = realpath(path);
    let rpath_str = rpath.to_string_lossy();
    
    // Equivalent to Python's rpath[:8] == "/safedir"
    if rpath_str.starts_with("/safedir") {
        return ReturnType::ErrorCode(-1);
    }
    
    // Equivalent to Python's open(path, 'w') with try/except
    match File::create(path) {
        Ok(fp) => ReturnType::FileHandle(fp),
        Err(e) => {
            println!("Error: {}", e);
            ReturnType::Nothing
        }
    }
}

fn main() {
    let file_name = "/tmp/foo";
    println!("{}", return_object(file_name));
}