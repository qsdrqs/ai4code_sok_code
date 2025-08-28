fn int_to_string(input: i32) -> String {
    // Convert the integer to a `String`.
    let buffer = input.to_string();

    // Print the resulting string.
    println!("you have entered: {}", buffer);

    // Return the string (so the caller could use it, if desired).
    buffer
}

fn main() {
    int_to_string(7_000_000);
}