use std::path::{PathBuf, Component};

/// Creates a PathBuf from a string, but only if the path is inside "/safedir".
///
/// This function mimics the behavior of the Python original. It checks if the
/// path's first two components are the root ("/") and a directory named "safedir".
///
/// # Arguments
///
/// * `p` - A string slice representing the file path.
///
/// # Returns
///
/// * `Some(PathBuf)` if the path starts with "/safedir".
/// * `None` otherwise.
fn path_from_str(p: &str) -> Option<PathBuf> {
    let path = PathBuf::from(p);

    // Get an iterator over the path's components.
    let mut components = path.components();

    // Check if the first component is the root directory ("/") and
    // the second component is the directory "safedir".
    if components.next() == Some(Component::RootDir) &&
       components.next() == Some(Component::Normal("safedir".as_ref())) {
        // If the check passes, return the owned PathBuf wrapped in Some.
        Some(path)
    } else {
        // Otherwise, return None.
        None
    }
}

fn main() {
    // The `{:?}` debug formatter is used to print the Option enum clearly.
    println!("{:?}", path_from_str("/safedir/junk"));
    println!("{:?}", path_from_str("/bin"));
    println!("{:?}", path_from_str("/safedir/doesntexist"));
}