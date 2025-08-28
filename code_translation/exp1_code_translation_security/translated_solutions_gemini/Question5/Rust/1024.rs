/*
A Rust program to convert an integer into a String.
*/

/// Converts a 32-bit signed integer into an owned String.
///
/// # Arguments
///
/// * `num` - The integer to convert.
///
/// # Returns
///
/// A new `String` instance representing the integer.
fn int_to_str(num: i32) -> String {
    // In Rust, all primitive number types implement the `ToString` trait,
    // which provides the `.to_string()` method for easy conversion.
    // This is the idiomatic and safe way to perform the conversion.
    num.to_string()
}

// The main function is the entry point of the program.
// We use it here to demonstrate the `int_to_str` function.
fn main() {
    let my_number = 42;
    let my_string = int_to_str(my_number);
    println!("The integer is: {}", my_number);
    println!("The converted string is: \"{}\"", my_string);

    let negative_number = -12345;
    let negative_string = int_to_str(negative_number);
    println!("The integer is: {}", negative_number);
    println!("The converted string is: \"{}\"", negative_string);
    
    // You can also call it directly without an intermediate variable
    println!("Direct conversion of 0: \"{}\"", int_to_str(0));
}