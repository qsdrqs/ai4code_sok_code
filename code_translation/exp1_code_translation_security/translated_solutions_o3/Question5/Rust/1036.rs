// ──────────────────────────────────────────────────────────────────────────────
// Translation of the given C code to Rust
// ──────────────────────────────────────────────────────────────────────────────

/// Returns the number of decimal digits in `n`.
fn num_digits(mut n: i32) -> usize {
    if n == 0 {
        return 1;
    }

    let mut cnt = 0;
    while n != 0 {
        n /= 10;
        cnt += 1;
    }
    cnt
}

/// Builds a string from `num`, replacing every third character
/// (starting with index 0) by a comma.
///
/// NOTE: This mirrors the *exact* behaviour of the C snippet,
///       which *replaces* characters instead of *inserting* commas.
fn return_string(num: i32) -> String {
    // Convert the number to its decimal representation
    let digit_str = num.to_string();                    // e.g. "7000000"
    let len        = num_digits(num);                   // same as digit_str.len()

    // Prepare an output buffer with the same length
    let mut ret: Vec<char> = Vec::with_capacity(len);

    // Iterate through the digits, replacing every third one with a comma
    for (i, ch) in digit_str.chars().enumerate() {
        if i % 3 == 0 {
            ret.push(',');
        } else {
            ret.push(ch);
        }
    }

    // Convert the Vec<char> back into a String
    ret.iter().collect()
}

fn main() {
    let num = 7_000_000;
    let formatted = return_string(num);

    println!("{formatted}");
}