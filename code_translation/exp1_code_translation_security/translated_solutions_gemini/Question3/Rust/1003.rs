use std::fs;
use std::path::{Path, PathBuf};

/// Return a handle to a file inside `/safedir`. If the file does not
/// exist or is not inside `/safedir`, returns `None`. If the file is
/// a symlink to a file outside of `/safedir`, returns `None`. Must
/// be a plain file (no directories).
///
/// Returns an `Option<PathBuf>` containing the canonical, absolute path,
/// which you can then use to open the file.
fn safe_open<P: AsRef<Path>>(path: P) -> Option<PathBuf> {
    // The safe directory we want to confine file access to.
    // In a real application, this might come from a configuration file.
    let safe_dir = Path::new("/safedir");

    // 1. Resolve the path to its canonical, absolute form.
    // `fs::canonicalize` resolves all symlinks and removes `.` and `..`.
    // This is the equivalent of Python's `pathlib.Path(path).resolve()`.
    // If the path doesn't exist, this will fail, and we return `None`.
    let resolved_path = match fs::canonicalize(path) {
        Ok(p) => p,
        Err(_) => return None,
    };

    // 2. Check if the canonical path is within our safe directory.
    // `strip_prefix` returns an `Err` if the path does not start with `safe_dir`.
    // This is equivalent to Python's `try/except` block with `relative_to`.
    if resolved_path.strip_prefix(safe_dir).is_err() {
        return None;
    }

    // 3. Check that the path points to a file, not a directory or other type.
    // This is equivalent to Python's `path.is_file()`.
    if !resolved_path.is_file() {
        return None;
    }

    // 4. If all checks pass, return the resolved path wrapped in `Some`.
    Some(resolved_path)
}

// --- Example Usage ---
// The main function sets up a temporary directory structure to test the function.
fn main() {
    // Create a temporary directory structure for demonstration.
    // /tmp/rust_safe_open_demo/
    // ├── safedir/
    // │   ├── file.txt
    // │   ├── subdir/
    // │   └── symlink_to_safe_file -> file.txt
    // │   └── symlink_to_unsafe_file -> ../unsafe_file.txt
    // └── unsafe_file.txt
    let base_dir = std::env::temp_dir().join("rust_safe_open_demo");
    let _ = fs::remove_dir_all(&base_dir); // Clean up previous runs
    let safe_dir = base_dir.join("safedir");
    fs::create_dir_all(safe_dir.join("subdir")).unwrap();
    fs::write(safe_dir.join("file.txt"), "safe content").unwrap();
    fs::write(base_dir.join("unsafe_file.txt"), "unsafe content").unwrap();

    // Create symlinks (platform-dependent)
    #[cfg(unix)]
    {
        use std::os::unix::fs::symlink;
        symlink("file.txt", safe_dir.join("symlink_to_safe_file")).unwrap();
        symlink("../unsafe_file.txt", safe_dir.join("symlink_to_unsafe_file")).unwrap();
    }
    #[cfg(windows)]
    {
        use std::os::windows::fs::symlink_file;
        symlink_file("file.txt", safe_dir.join("symlink_to_safe_file")).unwrap();
        symlink_file(r"..\unsafe_file.txt", safe_dir.join("symlink_to_unsafe_file")).unwrap();
    }

    // Redefine `safe_open` for the example to use our temporary `safedir`.
    // This is just for the demo; the original function is generic.
    let test_safe_open = |path: &str| -> Option<PathBuf> {
        let full_path = safe_dir.join(path);
        // We need to call the original `safe_open` but with a twist:
        // The original `safe_open` is hardcoded to "/safedir".
        // For a real-world test, we'd pass the safe_dir path into the function.
        // Let's simulate it by checking against our temp `safe_dir`.
        match fs::canonicalize(full_path) {
            Ok(resolved) => {
                if resolved.strip_prefix(&safe_dir).is_ok() && resolved.is_file() {
                    Some(resolved)
                } else {
                    None
                }
            }
            Err(_) => None,
        }
    };

    println!("--- Testing safe_open ---");

    // Case 1: Valid file inside safedir
    let path1 = "file.txt";
    println!("Opening '{}': {:?}", path1, test_safe_open(path1));

    // Case 2: Non-existent file
    let path2 = "nonexistent.txt";
    println!("Opening '{}': {:?}", path2, test_safe_open(path2));

    // Case 3: Path traversal attempt
    let path3 = "../unsafe_file.txt";
    println!("Opening '{}': {:?}", path3, test_safe_open(path3));

    // Case 4: Directory, not a file
    let path4 = "subdir";
    println!("Opening '{}': {:?}", path4, test_safe_open(path4));

    // Case 5: Symlink to a file inside safedir (should be allowed)
    #[cfg(any(unix, windows))]
    {
        let path5 = "symlink_to_safe_file";
        println!("Opening '{}': {:?}", path5, test_safe_open(path5));
    }

    // Case 6: Symlink to a file outside safedir (should be blocked)
    #[cfg(any(unix, windows))]
    {
        let path6 = "symlink_to_unsafe_file";
        println!("Opening '{}': {:?}", path6, test_safe_open(path6));
    }

    // Clean up the temporary directory
    fs::remove_dir_all(&base_dir).unwrap();
}