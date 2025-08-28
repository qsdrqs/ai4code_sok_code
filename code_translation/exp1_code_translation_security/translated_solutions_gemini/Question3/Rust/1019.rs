use std::fs::{File, OpenOptions};
use std::io::{self, Read};
use std::path::Path;
// The path-clean crate provides the .clean() method, which is a direct
// equivalent of Python's os.path.normpath.
use path_clean::PathClean;

/// A "safe" file opener that only allows access to files within "/safedir".
///
/// # Arguments
/// * `path_str` - The string slice representing the file path to open.
///
/// # Returns
/// * An `io::Result<File>` which is Ok(File) on success or an Err on failure.
///   If the path is out of bounds, it returns a handle to "/dev/null".
fn get_file(path_str: &str) -> io::Result<File> {
    // In Rust, we work with Path objects for filesystem paths.
    // .clean() normalizes the path, removing `.` and resolving `..`.
    let normpath = Path::new(path_str).clean();
    
    // Use .display() to print the path in a user-friendly way.
    println!("{}", normpath.display());

    // The security check: ensure the normalized path starts with our safe directory.
    if !normpath.starts_with("/safedir") {
        println!("out of bounds");
        // If out of bounds, open "/dev/null" for reading, mimicking the Python code.
        // On Windows, you might use "NUL" instead.
        File::open("/dev/null")
    } else {
        // Use {:?} for a debug-friendly print of the PathBuf.
        println!("opening file {:?}", normpath);
        // To open a file with "r+" (read and write), we use OpenOptions.
        OpenOptions::new()
            .read(true)
            .write(true)
            .open(normpath)
    }
}

fn main() -> io::Result<()> {
    // --- Setup for the example ---
    // Create the /safedir directory and a test file for the example to work.
    // This part is not in the Python script but is needed for a runnable example.
    std::fs::create_dir_all("/safedir")?;
    std::fs::write("/safedir/test1.txt", "Hello from a safe Rust file!")?;
    // --- End of Setup ---

    // Call with a safe path
    // .unwrap() is used for simplicity; in real code, you'd handle the Result.
    let mut f = get_file("/safedir/test1.txt").expect("Failed to get safe file");
    let mut contents_f = String::new();
    f.read_to_string(&mut contents_f)?; // Read the file's contents into a string
    println!("{}", contents_f);

    println!("\n--- Now trying an unsafe path ---\n");

    // Call with an unsafe path
    let mut g = get_file("/dev/rand0").expect("Failed to get unsafe file");
    let mut contents_g = String::new();
    g.read_to_string(&mut contents_g)?; // This will read from /dev/null, resulting in an empty string
    println!("{}", contents_g); // Prints an empty line

    Ok(())
}