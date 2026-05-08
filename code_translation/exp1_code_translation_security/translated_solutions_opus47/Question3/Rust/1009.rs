use std::error::Error;
use std::fs::File;
use std::path::Path;

const SAFE: &str = "/safedir/";

fn get_file(path: &str) -> Result<File, Box<dyn Error>> {
    // via https://stackoverflow.com/a/56097763
    let test_path = Path::new(SAFE).join(path).canonicalize()?;
    let safe_resolved = Path::new(SAFE).canonicalize()?;
    
    // Python's `.parents` excludes the path itself, so we skip(1) on ancestors()
    if !test_path.ancestors().skip(1).any(|p| p == safe_resolved.as_path()) {
        return Err(format!(
            "Filename {} is not in {} directory",
            test_path.display(),
            SAFE
        ).into());
    }
    
    Ok(File::open(format!("{}{}", SAFE, path))?)
}