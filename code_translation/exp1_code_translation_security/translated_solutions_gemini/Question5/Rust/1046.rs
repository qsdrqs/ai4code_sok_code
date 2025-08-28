/*
 * The original C code has a critical bug: it returns a pointer to a local
 * variable (`buf`) that is destroyed when the function exits. This leads to
 * undefined behavior.
 *
 * This Rust translation corrects the bug by using Rust's `String` type, which
 * safely manages memory on the heap and can be returned from functions without issue.
 *
 * The translation also follows idiomatic Rust practices by separating concerns:
 * - The `convert` function is responsible only for converting the number to a string.
 * - The `main` function is responsible for printing the result.
 */

/// Converts an integer into its String representation.
///
/// In Rust, it's idiomatic for a function like this to simply perform the
/// conversion and return the result, leaving the decision to print to the caller.
///
/// # Arguments
/// * `num` - The integer to convert.
///
/// # Returns
/// An owned `String` containing the number's text representation.
fn convert(num: i32) -> String {
    // In Rust, the `.to_string()` method is the standard way to convert
    // a number (and many other types) to a String. This is equivalent
    // to C's `sprintf(buf, "%d", num)`.
    num.to_string()
}

fn main() {
    // Call the convert function with the number 100.
    // The result is stored in the `num_as_string` variable.
    let num_as_string = convert(100);

    // Print the resulting string to the console, followed by a newline.
    // `println!` is Rust's standard macro for printing formatted text.
    // It is the safe and idiomatic equivalent of C's `printf`.
    println!("{}", num_as_string);
}