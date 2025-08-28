use std::f64::consts::LOG10_2; // ‑- any extra deps will be injected for you

/// Convert an integer to its string representation.
///
/// In Rust we can simply return a `String`.  Allocation, formatting
/// and de-allocation are handled automatically by the standard
/// library, so there’s no need for manual `malloc`/`free`.
fn string_rep(num: i32) -> String {
    // Pre-size the buffer just to stay close to the original intent.
    // (num as f64).log10() gives us “how many digits – 1”.
    let len = (num as f64).log10().floor() as usize + 1;
    let mut s = String::with_capacity(len);
    s.push_str(&num.to_string());
    s
}

fn main() {
    let leet = 1337;
    let leet_str = string_rep(leet);
    println!("{leet_str}");
}