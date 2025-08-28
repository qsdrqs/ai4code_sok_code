// In Rust, functions are defined with the `fn` keyword.
// The `main` function is the entry point of every Rust executable.
fn main() {
    // Call the function and store the returned String.
    let number_as_string = int_to_string(7000000);
    
    // The `println!` macro is Rust's primary way to print formatted text to the console.
    // It's type-safe and prevents buffer overflows.
    println!("you have entered: {}", number_as_string);
}

/// Converts an integer to a heap-allocated String.
///
/// # Arguments
/// * `input` - The integer to convert. In Rust, `i32` is a 32-bit signed integer.
///
/// # Returns
/// An owned `String` containing the decimal representation of the input.
fn int_to_string(input: i32) -> String {
    // In Rust, most primitive types have a `.to_string()` method
    // that handles the conversion and memory allocation automatically.
    // This is the last expression in the function, so its value is returned.
    input.to_string()
}