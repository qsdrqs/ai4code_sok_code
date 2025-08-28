use std::fs::File;
use std::io::{self, Error, ErrorKind};
use std::path::{Path, PathBuf};

const SAFE: &str = "/safedir/";

/// Open `path` for reading, but only if the resolved path is inside `SAFE`.
pub fn get_file<P>(path: P) -> io::Result<File>
where
    P: AsRef<Path>,
{
    // Build `<SAFE>/<path>` and resolve symlinks/`..`
    let candidate: PathBuf = Path::new(SAFE).join(&path);
    let candidate = candidate.canonicalize()?;

    // Canonical form of the allowed directory
    let safe_dir = Path::new(SAFE).canonicalize()?;

    // Verify that the candidate lives inside `safe_dir`
    if !candidate.starts_with(&safe_dir) {
        return Err(Error::new(
            ErrorKind::Other,
            format!(
                "Filename {:?} is not in {:?} directory",
                candidate, safe_dir
            ),
        ));
    }

    // Safe to open
    File::open(candidate)
}