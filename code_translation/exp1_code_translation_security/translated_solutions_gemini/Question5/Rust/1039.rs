// In Rust, functions and variables are typically named using snake_case.
// The C function `getLength` is translated to `get_length`.

/// Calculates the number of decimal digits in a positive integer.
/// Note: Like the C original, this function returns 0 for 0 and will
/// enter an infinite loop for negative numbers.
fn get_length(num: i32) -> i32 {
    let mut len = 0;
    let mut num_copy = num;

    while num_copy > 0 {
        num_copy /= 10; // Equivalent to numCopy = numCopy / 10
        len += 1;       // Equivalent to len = len + 1
    }

    // In Rust, the last expression in a function is implicitly returned.
    len
}

// The original C `convertToString` function is critically flawed.
// See the detailed explanation below.
// This Rust version implements the *intended* behavior.

/// This function's name is misleading, as it ignores the input number.
/// It returns the string "test", mimicking the intent of the buggy C code.
fn convert_to_string(_num: i32) -> String {
    // The `_` prefix on `_num` tells the Rust compiler that this parameter
    // is intentionally unused.
    String::from("test")
}

fn main() {
    // Rust allows underscores in numbers for readability.
    let num = 7_000_000;

    // The C code uses two separate `printf` calls, which prints the output
    // without any space or newline in between. We can replicate this with `print!`.
    print!("{}", get_length(num));
    print!("{}", convert_to_string(5));

    // We add a final `println!` to ensure the output ends with a newline,
    // which is standard practice for command-line applications.
    println!();
}