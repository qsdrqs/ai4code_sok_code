use std::fs::{self, File};
use std::path::Path;

/// Checks if a file path, after resolving symlinks, is located within the "/safedir" directory.
/// This is a Rust translation of the `in_safe` nested function from the Python code.
fn is_in_safe_dir(path: &Path) -> bool {
    // Python: cwd = os.path.normcase("/safedir")
    // We define the safe directory path. Case normalization is handled implicitly
    // by `Path` operations on different operating systems, so an explicit call is not needed.
    let safe_dir = Path::new("/safedir");

    // Python: path = os.path.normcase(os.path.dirname(os.path.realpath(fname)))
    // We chain the equivalent Rust operations, handling potential errors at each step.

    // 1. `os.path.realpath` -> `fs::canonicalize`
    //    Resolves symlinks and returns the canonical, absolute path.
    //    If the path doesn't exist or is invalid, it returns an error.
    let canonical_path = match fs::canonicalize(path) {
        Ok(p) => p,
        Err(_) => return false, // Path is not valid, so it cannot be safe.
    };

    // 2. `os.path.dirname` -> `path.parent()`
    //    Gets the parent directory of the canonical path. This returns an `Option`.
    let parent_dir = match canonical_path.parent() {
        Some(p) => p,
        None => return false, // No parent (e.g., root "/"), so it's not in "/safedir".
    };

    // Python: return os.path.commonpath((path, cwd)) == cwd
    // The idiomatic Rust equivalent is `starts_with`. It checks if the resolved
    // parent directory path begins with "/safedir", effectively ensuring the
    // file is inside the safe directory or one of its subdirectories.
    parent_dir.starts_with(safe_dir)
}

/// Opens a file only if it is located within a designated safe directory ("/safedir").
///
/// This function translates the Python `openFile` logic. It uses `is_in_safe_dir`
/// to perform the security check before attempting to open the file.
///
/// # Arguments
///
/// * `path` - A string slice that holds the path to the file to open.
///
/// # Returns
///
/// * `Some(File)` if the file is in the safe directory and was successfully opened.
/// * `None` if the path is outside the safe directory or if the file could not be opened.
pub fn open_file(path: &str) -> Option<File> {
    // Python: if in_safe(path):
    if is_in_safe_dir(Path::new(path)) {
        // Python: file = open(path, "r"); return file
        // `File::open` attempts to open the file for reading. It returns a `Result`.
        // `.ok()` converts the `Result<File, Error>` into an `Option<File>`,
        // which is `Some(file)` on success and `None` on failure. This perfectly
        // matches the Python function's behavior of returning a file object or None.
        File::open(path).ok()
    } else {
        // Python: else: return None
        None
    }
}

fn main() {
    // Python: print(openFile("/env.sh"))
    // We call the function with a path that is outside the safe directory.
    // The `{:?}` debug formatter prints the `Option` result, which will be `None`
    // in this case, correctly mimicking the Python script's output.
    println!("{:?}", open_file("/env.sh"));
}