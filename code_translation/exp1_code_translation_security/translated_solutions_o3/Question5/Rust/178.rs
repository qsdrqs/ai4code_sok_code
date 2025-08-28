// main.rs
use std::f64;   // to be on par with the C `round` call

// ------------------------------------------------------------
// The `convert_to_string` function is the Rust counterpart of
// the C function that used to live inside `main`.
// ------------------------------------------------------------
fn convert_to_string(num: i32) -> String {
    // 1. First create the decimal representation of `num`
    let string = num.to_string();

    // 2. `round(strlen(string) / 3)`  (note: `string.len()` is byte length)
    let num_of_commas =
        ((string.len() as f64) / 3.0).round() as usize;
    println!("{}", num_of_commas);                 // same `printf` as in C

    // 3. Allocate room for the new string (`digits` + `commas`)
    let mut new_string = String::with_capacity(string.len() + num_of_commas);

    // 4. Re-implement the original for-loop
    //
    //    The C version iterated up to  strlen + 1 + numOfCommas.
    //    The extra “+1” accounts for the C terminating NUL byte –
    //    irrelevant for Rust strings, so we keep exactly
    //    `string.len() + num_of_commas` iterations.
    let mut i = 0usize;
    while i < string.len() + num_of_commas {
        // Copy the current digit if we still have one
        if i < string.len() {
            let byte = string.as_bytes()[i];
            new_string.push(byte as char);         // same byte-wise copy
            print!("{}", byte);                    // prints ASCII code as C did
        }

        // Insert a comma every 3rd character (C’s odd rule preserved)
        if i % 3 == 0 && i != string.len() + num_of_commas {
            print!("s");                           // the mysterious extra “s”
            new_string.push(',');
        }

        i += 1;
    }

    new_string
}

// ------------------------------------------------------------
fn main() {
    // This line is equivalent to `printf("%s", convertToString(2000));`
    println!("{}", convert_to_string(2000));
}