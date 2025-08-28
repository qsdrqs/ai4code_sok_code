// In Rust, we don't need to include headers like in C for basic I/O or string
// manipulation. The standard library is automatically linked. We bring specific
// items into scope with the `use` keyword, but for this code, it's not even
// necessary as `String`, `println!`, and integer methods are in the prelude.

/// This is a corrected translation of the C `convertInt` function's *intent*.
/// The original C function was fundamentally broken:
/// 1. It returned a pointer to a local stack variable (`output`), which is
///    undefined behavior as the memory is deallocated when the function exits.
///    Rust's compiler would prevent this error at compile time.
/// 2. The loop condition `output[i] != '\0'` read from uninitialized memory.
/// 3. It produced the digits in reverse order (e.g., 123 -> "321").
///
/// This Rust version correctly allocates a `String` on the heap and returns
/// ownership of it. It still mimics the reverse-digit behavior of the original.
fn convert_int(mut input: i32) -> String {
    // Handle the edge case of 0, which the original C code did not.
    if input == 0 {
        return "0".to_string();
    }

    // A `String` in Rust is a growable, heap-allocated string.
    let mut output = String::new();
    
    // Loop until all digits are processed.
    while input > 0 {
        // Get the last digit: (input % 10)
        // Convert it to a character: `as u8 + b'0'` is the idiomatic way.
        // `b'0'` is a byte literal for the ASCII value of '0'.
        let last_digit_char = ((input % 10) as u8 + b'0') as char;
        
        // Append the character to our string.
        output.push(last_digit_char);
        
        // Remove the last digit from the input.
        input /= 10;
    }
    
    output
}

/// This is the translation of `convertIntAI`.
/// In C, `sprintf` is a common way to format values into a string.
/// In Rust, most types that can be displayed implement the `ToString` trait,
/// so we can simply call the `.to_string()` method. This is much safer
/// and more concise.
fn convert_int_ai(input: i32) -> String {
    // This is the idiomatic Rust equivalent of `sprintf(buf, "%d", input)`.
    input.to_string()
}

fn main() {
    // Call the function and bind the returned `String` to the `output` variable.
    // Rust uses snake_case for function and variable names by convention.
    let output = convert_int_ai(7_000_000); // You can use `_` as a numeric separator.

    // `println!` is Rust's macro for printing a line to the console.
    // The `{}` is a placeholder for the variable.
    println!("{}", output);

    // Note: There is no need to `free(output)` like in C.
    // The `String` type in Rust manages its own memory. When `output` goes
    // out of scope at the end of `main`, its memory is automatically and
    // safely deallocated. This prevents the memory leak present in the
    // original C `main` function.
}