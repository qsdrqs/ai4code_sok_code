use std::fs::File;
use std::io;
use std::path::{Path, PathBuf, Component};

/// Normalizes a path by collapsing `.` and `..` components.
/// This mimics Python's `os.path.normpath` behavior.
fn normalize<P: AsRef<Path>>(path: P) -> PathBuf {
    let mut components = path.as_ref().components();
    let mut normalized = PathBuf::new();

    for component in components {
        match component {
            Component::CurDir => {} // Skip current directory
            Component::ParentDir => {
                normalized.pop(); // Remove the last component if possible
            }
            _ => {
                normalized.push(component.as_os_str()); // Push everything else
            }
        }
    }

    normalized
}

/// Translates the Python function `path2obj` into Rust.
///
/// Returns:
/// - `Ok(Some(file))` if a directory named "safedir" is found in the path hierarchy.
/// - `Ok(None)` if no such directory is found.
/// - `Err(e)` if the file could not be opened.
pub fn path2obj<P: AsRef<Path>>(file_path: P) -> io::Result<Option<File>> {
    let file = File::open(file_path.as_ref())?;
    let mut current_dir = file_path.as_ref().parent();

    while let Some(dir) = current_dir {
        let normalized_dir = normalize(dir);
        if let Some(name) = normalized_dir.file_name().and_then(|s| s.to_str()) {
            if name == "safedir" {
                return Ok(Some(file));
            }
        }
        current_dir = dir.parent();
    }

    Ok(None)
}