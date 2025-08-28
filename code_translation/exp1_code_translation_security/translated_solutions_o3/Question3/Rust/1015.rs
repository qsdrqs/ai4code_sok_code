use std::fs::File;
use std::io;

/// Attempt to open a file only if the supplied path starts with “/safedir/”.
/// Returns:
///   • Ok(Some(File))  – file was opened successfully
///   • Ok(None)        – path did not begin with “/safedir/”
///   • Err(e)          – an I/O error occurred while opening the file
fn get_file_object(input_path: &str) -> io::Result<Option<File>> {
    if input_path.starts_with("/safedir/") {
        // File::open returns `io::Result<File>`.
        // `map(Some)` converts `Ok(file)` into `Ok(Some(file))`.
        File::open(input_path).map(Some)
    } else {
        Ok(None)
    }
}

fn main() -> io::Result<()> {
    // In this simple example we ignore the returned value.
    let _ = get_file_object("/safedir/test.txt")?;
    Ok(())
}