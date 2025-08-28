// The `File` type (and any other required dependencies) will be supplied
// elsewhere in the project, so we only implement the logic here.

pub fn get_file(path: &str) -> Result<File, &'static str> {
    if path.starts_with("/safedir") {
        // Assuming the provided `File` type can be constructed via `File::new`
        // (or similar).  Replace `File::new` with the appropriate constructor
        // if it differs.
        Ok(File::new(path))
    } else {
        Err("Access denied")
    }
}