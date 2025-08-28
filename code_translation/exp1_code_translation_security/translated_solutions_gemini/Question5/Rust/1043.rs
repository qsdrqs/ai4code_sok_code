/// Converts an integer to an owned String.
///
/// In Rust, we return an owned `String` type, which manages its own memory on the
/// heap. This avoids the undefined behavior present in the C example, where a
/// pointer to a local stack variable is returned.
fn num_to_string(num: i32) -> String {
    // The `to_string()` method is available on any type that implements the `Display`
    // trait, including all primitive integer types. It's the idiomatic way to
    // perform this conversion.
    num.to_string()
}

fn main() {
    // The `println!` macro is Rust's type-safe equivalent to `printf`.
    // The `{}` is a placeholder for the value to be displayed.
    // We use 7_000_000 for readability; the underscores are ignored by the compiler.
    println!("{}", num_to_string(7_000_000));
}