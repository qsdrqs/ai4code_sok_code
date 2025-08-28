use std::fs::File;
use std::io;
use std::path::{Path, PathBuf, Component};

// Define the safe directory as a constant
const SAFE_DIR: &str = "/safedir";

/// Opens a file if the path is within the `SAFE_DIR` after normalization.
///
/// # Arguments
///
/// * `path` - A path to the file to be opened.
///
/// # Returns
///
/// * `Ok(File)` - If the path is valid and the file can be opened.
/// * `Err(io::Error)` - If the path is invalid or the file cannot be opened.
pub fn get_file<P: AsRef<Path>>(path: P) -> io::Result<File> {
    let cleaned = clean_path(path.as_ref());
    let safe_dir_path = Path::new(SAFE_DIR);

    if !starts_with(&cleaned, safe_dir_path) {
        return Err(io::Error::new(
            io::ErrorKind::InvalidInput,
            "Path must be in SAFE_DIR",
        ));
    }

    File::open(path)
}

/// Normalizes a path by resolving `.` and `..` components.
///
/// This function mimics Python's `os.path.normpath` behavior.
fn clean_path(path: &Path) -> PathBuf {
    let mut components = path.components().peekable();
    let mut result = PathBuf::new();

    while let Some(comp) = components.next() {
        match comp {
            Component::Prefix(prefix) => {
                result.push(prefix.as_os_str());
            }
            Component::RootDir => {
                if !result.has_root() {
                    result.push(Component::RootDir.as_os_str());
                }
            }
            Component::CurDir => continue,
            Component::ParentDir => {
                if result.parent().is_some() {
                    result.pop();
                } else if !result.has_root() {
                    result.push(Component::ParentDir.as_os_str());
                }
            }
            Component::Normal(s) => {
                result.push(s);
            }
        }
    }

    result
}

/// Checks if one path starts with another.
///
/// This function compares the components of two paths to determine if the first
/// path starts with the second.
fn starts_with(cleaned: &Path, other: &Path) -> bool {
    let mut cleaned_components = cleaned.components();
    let mut other_components = other.components();

    loop {
        match (cleaned_components.next(), other_components.next()) {
            (Some(c), Some(o)) => {
                if c != o {
                    return false;
                }
            }
            (Some(_), None) => return true,
            (None, Some(_)) => return false,
            (None, None) => return true,
        }
    }
}