use std::path::{Component, Path, PathBuf};

/// Replicates the Python `path_from_str` logic.
/// Returns `Some(PathBuf)` only when the path starts with `/` and `safedir`.
fn path_from_str<P>(p: P) -> Option<PathBuf>
where
    P: AsRef<Path>,
{
    let path = p.as_ref();
    let mut components = path.components();

    // First component must be the root `/`
    match components.next() {
        Some(Component::RootDir) => {}
        _ => return None,
    }

    // Second component must be `safedir`
    match components.next() {
        Some(Component::Normal(seg)) if seg == "safedir" => {}
        _ => return None,
    }

    // All good – return the full path as an owned `PathBuf`
    Some(path.to_path_buf())
}

fn main() {
    println!("{:?}", path_from_str("/safedir/junk"));
    println!("{:?}", path_from_str("/bin"));
    println!("{:?}", path_from_str("/safedir/doesntexist"));
}