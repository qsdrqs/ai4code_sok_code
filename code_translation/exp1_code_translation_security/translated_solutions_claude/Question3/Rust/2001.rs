use std::fs::File;
use std::io::Result as IoResult;
use std::path::{Path, PathBuf};

const SAFE_DIR: &str = "/safedir";

#[derive(Debug)]
struct PathError(String);

impl std::fmt::Display for PathError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}", self.0)
    }
}

impl std::error::Error for PathError {}

fn get_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    let normalized_path = Path::new(path)
        .canonicalize()
        .unwrap_or_else(|_| PathBuf::from(path));
    
    let normalized_str = normalized_path.to_string_lossy();
    
    if !normalized_str.starts_with(SAFE_DIR) {
        return Err(Box::new(PathError("Path must be in SAFE_DIR".to_string())));
    }
    
    let file = File::open(path)?;
    Ok(file)
}