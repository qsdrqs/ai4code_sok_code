/// Converts a 32-bit signed integer to a heap-allocated String.
///
/// # Arguments
///
/// * `num` - The i32 integer to convert.
///
/// # Returns
///
/// A `String` representation of the number.
fn int2str(num: i32) -> String {
    // In Rust, all primitive number types have a `to_string()` method
    // that handles the conversion to a heap-allocated String.
    // This is the most direct and idiomatic way to perform the conversion.
    num.to_string()
}

// Example of how to use the function
fn main() {
    let number = 42;
    let number_as_string: String = int2str(number);
    println!("The integer {} is the string '{}'", number, number_as_string);

    let negative_number = -123;
    let negative_as_string: String = int2str(negative_number);
    println!("The integer {} is the string '{}'", negative_number, negative_as_string);
}