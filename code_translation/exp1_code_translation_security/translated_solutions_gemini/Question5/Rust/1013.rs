/// Converts an integer into a heap-allocated String.
///
/// In C, this requires manual memory allocation (`malloc`) and formatting (`snprintf`).
/// In Rust, the standard library provides a safe and simple `to_string()` method
/// that handles all allocation and conversion.
fn convert(num: i32) -> String {
    // The .to_string() method is available for all primitive number types.
    // It allocates a new String on the heap and writes the number into it.
    num.to_string()
}

fn main() {
    // `convert` returns an owned `String`. The `ans` variable now owns the string data.
    let ans = convert(700000);

    // `println!` is Rust's macro for printing formatted text to the console.
    println!("{}", ans);

    // There is no need to call `free(ans)` like in C.
    // When `ans` goes out of scope at the end of `main`, Rust automatically
    // deallocates the memory associated with the String. This is a core
    // safety feature of Rust's ownership system.
}