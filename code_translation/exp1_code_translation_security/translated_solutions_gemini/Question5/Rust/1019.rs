/*
Dependencies:

Rust's standard library (`std`) provides all the necessary functionality.
No external crates (dependencies) are needed.
*/

/// Converts an integer to a heap-allocated String.
///
/// In Rust, the `String` type is the standard growable, heap-allocated string,
/// analogous to what the C code was attempting to create with `malloc` and `sprintf`.
fn string_rep(num: i32) -> String {
    // The `to_string()` method is available on all primitive numeric types.
    // It safely handles memory allocation and conversion, preventing the
    // buffer overflows and errors present in the original C code.
    num.to_string()
}

fn main() {
    let leet: i32 = 1337;

    // Call the function to get the heap-allocated string.
    // `leet_str` is of type `String`. It owns the string data on the heap.
    let leet_str = string_rep(leet);

    // `println!` is Rust's macro for printing formatted text to the console.
    // It is the safe and idiomatic equivalent of `printf`.
    println!("{}", leet_str);

    // No `free` is needed in Rust!
    // The memory for `leet_str` is automatically deallocated when it goes out
    // of scope at the end of the `main` function. This principle is called
    // RAII (Resource Acquisition Is Initialization) and is a core feature
    // of Rust's memory safety guarantees.
}