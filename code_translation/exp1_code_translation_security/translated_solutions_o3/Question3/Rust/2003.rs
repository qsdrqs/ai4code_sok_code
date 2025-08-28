use std::fs::File;
use std::io::{self, Read};
use std::path::{Component, PathBuf};

/// Tries to open `path_str`.
/// The path must resolve to an absolute path whose first
/// non-root component is exactly `safedir`; otherwise an
/// error is returned (mirroring the Python exception).
fn load(path_str: &str) -> io::Result<File> {
    // Obtain the absolute, canonical version of the path
    let path: PathBuf = std::fs::canonicalize(path_str)?;

    // Collect the components so we can examine them
    let mut comps = path.components();

    // Skip the root component (e.g. `/`)
    let _root = comps.next();

    // Check that a second component exists and is "safedir"
    let is_safe = matches!(comps.next(),
        Some(Component::Normal(os)) if os == "safedir");

    if !is_safe {
        return Err(io::Error::new(
            io::ErrorKind::Other,
            "Cannot access unsafe file",
        ));
    }

    // All good: open the file for reading
    File::open(path)
}

fn main() -> io::Result<()> {
    // 1) A file that *should* be allowed
    match load("/safedir/foo.txt") {
        Ok(mut file) => {
            let mut buf = String::new();
            file.read_to_string(&mut buf)?;
            println!("{}", buf);
        }
        Err(e) => eprintln!("Error: {}", e),
    }

    // 2) A file that *should* be rejected
    match load("/tmp/foo.txt") {
        Ok(mut file) => {
            let mut buf = String::new();
            file.read_to_string(&mut buf)?;
            println!("{}", buf);
        }
        Err(e) => eprintln!("Error: {}", e),
    }

    Ok(())
}