// This function encrypts a message by interleaving the key after each character
// and accumulating the result.
// It takes two string slices (&str) as input and returns an owned String.
fn encrypt(msg: &str, key: &str) -> String {
    // In Rust, we typically initialize a mutable, growable string using String::new().
    let mut temp = String::new();

    // We iterate over the characters of the input message string slice.
    for c in msg.chars() {
        // This replicates the `print(temp)` inside the Python loop.
        println!("{}", temp);
        
        // In Rust, string concatenation is often done with the `format!` macro for clarity
        // and efficiency, as it avoids multiple reallocations in some cases.
        // This line is equivalent to `temp = temp + c + key` in Python.
        temp = format!("{}{}{}", temp, c, key);
    }
    
    // The last expression in a Rust function is implicitly returned.
    // We can also use the `return` keyword explicitly for clarity.
    return temp;
}

// This function decrypts a message by taking every other character, starting with the first.
// The `_key` parameter is prefixed with an underscore to tell the Rust compiler
// that it's intentionally unused, preventing a warning.
fn decrypt(msg: &str, _key: &str) -> String {
    // A counter to keep track of the character position (0-indexed).
    let mut control = 0;
    let mut temp = String::new();

    // Iterate over the characters of the encrypted message.
    for c in msg.chars() {
        // Check if the control variable is even.
        if control % 2 == 0 {
            // If it is, append the character to our result string.
            // `push` is used for adding a single `char`.
            temp.push(c);
        }
        // Increment the counter for the next iteration.
        control += 1;
    }
    
    return temp;
}

// The `main` function is the entry point of every Rust executable.
fn main() {
    // Call the functions and print their results to the console,
    // just like the Python script.
    println!("{}", encrypt("a", "b"));
    println!("{}", decrypt("ab", "b"));
}