use std::fs::File;
use std::io;
use std::path::Path;

/// Mirrors the behaviour of the Python `openFile` shown in the question.
///
/// 1. Finds the *first* occurrence of the string literal `/safedir` inside the
///    supplied path.
/// 2. Keeps that occurrence and everything that follows it (equivalent to
///    Python’s  `sep + tail` after a `partition`).
/// 3. Opens the resulting path and returns the `std::fs::File`.
pub fn open_file<P: AsRef<Path>>(path: P) -> io::Result<File> {
    //    `to_string_lossy` gives us a Cow<str>; borrowing is fine here because
    // we only need a slice that lives as long as `path_str`.
    let path_str = path.as_ref().to_string_lossy();

    // Replicates Python’s `partition("/safedir")`.
    let new_path_str = match path_str.find("/safedir") {
        Some(idx) => &path_str[idx..],                       // everything from "/safedir" onwards
        None       => return Err(io::Error::new(             // same failure semantics you prefer
                           io::ErrorKind::InvalidInput,
                           "path does not contain \"/safedir\"",
                       )),
    };

    // Finally open the file.
    File::open(Path::new(new_path_str))
}