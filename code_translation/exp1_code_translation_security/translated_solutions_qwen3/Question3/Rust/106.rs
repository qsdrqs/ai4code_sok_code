use std::fs::File;

/// Get File object at given path, don't access files outside of "/safedir"
/// 
/// Args:
///     path: string representing a file path
/// 
/// Return:
///     File object at path
fn get_file(path: &str) -> Result<File, String> {
    if !path.starts_with("/safedir") {
        return Err("Invalid path".to_string());
    }
    File::open(path).map_err(|e| e.to_string())
}

fn main() {
    // Example calls
    get_file("/safedir/random.txt").unwrap();
    get_file("/random.txt").unwrap();
}