// In Rust, we must explicitly define the data structures we use.
// Here, we create a struct to represent the 'file' object from the Python code.
// It contains both 'name' and 'path' fields as owned Strings.
struct File {
    name: String,
    path: String,
}

/// Takes a reference to a File and returns a reference to its name.
fn get_file_path(file: &File) -> &str {
    // The original Python code had an unreachable line: `return file.path`.
    // A function can only return once, and the first `return` statement
    // immediately exits the function.
    //
    // This Rust translation correctly implements the actual behavior of the
    // Python code, which is to *always* return the `name` attribute.
    &file.name
}

// Example of how to use the function
fn main() {
    let my_file = File {
        name: String::from("document.txt"),
        path: String::from("/home/user/documents/document.txt"),
    };

    let file_name = get_file_path(&my_file);

    println!("The file name is: {}", file_name); // Output: The file name is: document.txt
}