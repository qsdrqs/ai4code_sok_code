fn main() {
    // Define the integer you want to convert.
    // Rust infers the type (i32) and makes the variable immutable by default.
    let number = -12;

    // Convert the integer to a string.
    // The `to_string()` method is available on all primitive types.
    // It allocates a new `String` on the heap to hold the result.
    let string = number.to_string();

    // Print the string.
    // `println!` is a macro that safely prints formatted text to the console.
    println!("{}", string);
}