use std::error::Error;
use std::fmt;
use std::fs::File;
use std::io;
use std::path::Path;

/// Custom error: path is outside the allowed directory.
#[derive(Debug)]
struct PathNotAllowed;

impl fmt::Display for PathNotAllowed {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(
            f,
            "filepath not allowed, only /safedir/... file paths are accessible"
        )
    }
}

impl Error for PathNotAllowed {}

/// Open a file if, and only if, the path starts with `/safedir`.
fn get_file_path<P: AsRef<Path>>(path: P) -> Result<File, Box<dyn Error>> {
    // Convert the path to a string for prefix checking.
    let path_str = path.as_ref().to_string_lossy();

    // Safety check, identical to Python’s .startswith("/safedir")
    if !path_str.starts_with("/safedir") {
        return Err(Box::new(PathNotAllowed));
    }

    // Try opening the file; forward any I/O error that may occur.
    Ok(File::open(path)?)
}

fn main() {
    let prefix = "/safedir/";

    // ---------- paths that should succeed ----------
    let good_paths = vec![
        format!("{}{}", prefix, "test1.txt"),
        format!("{}{}", prefix, "test.txt"),
    ];

    for path in &good_paths {
        // Open and immediately drop the handle (like f.close() in Python).
        match get_file_path(path) {
            Ok(_file) => { /* success, file dropped at end of scope */ }
            Err(e) => panic!("unexpected error while opening {:?}: {}", path, e),
        }
    }

    // ---------- file that does not exist ----------
    match get_file_path("/safedir/noexist.txt") {
        Err(e) => {
            // Down-cast to std::io::Error to check that it is 'NotFound'.
            if let Some(io_err) = e.downcast_ref::<io::Error>() {
                assert_eq!(io_err.kind(), io::ErrorKind::NotFound);
            } else {
                panic!("expected an io::ErrorKind::NotFound, got {}", e);
            }
        }
        Ok(_) => panic!("`/safedir/noexist.txt` unexpectedly opened"),
    }

    // ---------- path that violates the safedir prefix ----------
    match get_file_path("not/a/good/path.txt") {
        Err(e) => {
            assert!(e.downcast_ref::<PathNotAllowed>().is_some(),
                    "expected PathNotAllowed, got {}", e);
        }
        Ok(_) => panic!("`not/a/good/path.txt` should have been rejected"),
    }
}