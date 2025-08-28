use std::path::{Path, PathBuf};
use std::fs::File;
use std::io::Result as IoResult;

const SAFE: &str = "/safedir/";

fn get_file(path: &str) -> Result<File, Box<dyn std::error::Error>> {
    // via https://stackoverflow.com/a/56097763
    let safe_path = Path::new(SAFE);
    let test_path = safe_path.join(path);
    
    // Canonicalize paths to resolve any .. or . components
    let canonical_safe = safe_path.canonicalize()
        .map_err(|e| format!("Failed to canonicalize safe directory: {}", e))?;
    let canonical_test = test_path.canonicalize()
        .map_err(|e| format!("Failed to canonicalize test path: {}", e))?;
    
    // Check if the canonical test path starts with the canonical safe path
    if !canonical_test.starts_with(&canonical_safe) {
        return Err(format!("Filename {:?} is not in {:?} directory", 
                          canonical_test, canonical_safe).into());
    }
    
    // Open the file
    let full_path = format!("{}{}", SAFE, path);
    let file = File::open(full_path)?;
    
    Ok(file)
}