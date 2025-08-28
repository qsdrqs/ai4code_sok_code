use std::fs::File;
use std::io;
use std::path::{Component, Path, PathBuf};

/// Normalizes a path by collapsing `.` and `..` components.
fn resolve_components(path: &Path) -> PathBuf {
    let mut result = PathBuf::new();

    for component in path.components() {
        match component {
            Component::RootDir => {
                if result.as_os_str().is_empty() {
                    result.push("/");
                }
            }
            Component::CurDir => continue,
            Component::ParentDir => {
                if let Some(parent) = result.parent() {
                    result = parent.to_path_buf();
                } else {
                    // Cannot go beyond root, so just add ".."
                    result.push("..");
                }
            }
            Component::Normal(s) => {
                result.push(s);
            }
            _ => {}
        }
    }

    if result.as_os_str().is_empty() {
        PathBuf::from(".")
    } else {
        result
    }
}

/// Converts a relative path to an absolute one using the current working directory.
fn to_absolute_path(path: &Path) -> io::Result<PathBuf> {
    if path.is_absolute() {
        Ok(path.to_path_buf())
    } else {
        let current_dir = std::env::current_dir()?;
        Ok(current_dir.join(path))
    }
}

/// Attempts to open a file if its resolved path starts with `/safedir`.
pub fn open_file(filename: &str) -> io::Result<Option<File>> {
    let path = Path::new(filename);
    let absolute_path = to_absolute_path(path)?;
    let resolved = resolve_components(&absolute_path);

    let parts: Vec<_> = resolved.components().collect();

    if parts.len() >= 2 {
        if let Component::Normal(s) = parts[1] {
            if s == "safedir" {
                let file = File::open(resolved)?;
                return Ok(Some(file));
            }
        }
    }

    Ok(None)
}