use std::path::{Path, PathBuf};
use std::fs::File;

// Define the safe directory path
const SAFE: &str = "/safedir/";

/// Normalize a path by resolving `.` and `..` components without checking existence or resolving symlinks.
fn normalize_path<P: AsRef<Path>>(path: P) -> PathBuf {
    let mut result = PathBuf::new();
    let path = path.as_ref();

    for component in path.components() {
        match component {
            std::path::Component::Prefix(prefix) => {
                result.push(prefix.as_os_str());
            }
            std::path::Component::RootDir => {
                result.push(std::path::Component::RootDir.as_os_str());
            }
            std::path::Component::CurDir => {}
            std::path::Component::ParentDir => {
                if let Some(parent) = result.parent() {
                    result = parent.to_path_buf();
                } else {
                    result.push(component.as_os_str());
                }
            }
            std::path::Component::Normal(name) => {
                result.push(name);
            }
        }
    }

    if result.as_os_str().is_empty() {
        result.push(".");
    }

    result
}

/// Open a file from the SAFE directory, ensuring it's not a path traversal attempt.
pub fn get_file(path: &str) -> Result<File, String> {
    // Normalize the safe directory path
    let safe_dir_normalized = normalize_path(SAFE);

    // Construct the full path and normalize it
    let test_path = Path::new(SAFE).join(path);
    let test_path_normalized = normalize_path(&test_path);

    // Ensure the normalized path is within the safe directory
    if !test_path_normalized.starts_with(&safe_dir_normalized) {
        return Err(format!(
            "Filename {} is not in {} directory",
            test_path_normalized.display(),
            SAFE
        ));
    }

    // Open the file using the original path (not the normalized one)
    File::open(test_path).map_err(|e| format!("Failed to open file: {}", e))
}