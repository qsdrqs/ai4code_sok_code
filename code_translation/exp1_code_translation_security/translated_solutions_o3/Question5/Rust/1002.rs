use std::fmt::Write; // for `write!`

const BUFFER_SIZE: usize = 1024;

/// Re-creates the behaviour of the C version:
/// turns an `i64` into a decimal string and inserts
/// a comma every three digits when counting from the
/// right‐hand side.
///
/// Example: 1000 -> "1,000"
fn convert(value: i64) -> String {
    // First “buffer” – the plain decimal representation.
    // Equivalent to `snprintf(buf1, ..., "%ld", value);`
    let buf1 = value.to_string();
    let len   = buf1.len();

    // Allocate the second buffer with a bit of headroom.
    // Worst case, we need roughly one comma per three digits.
    let mut buf2 = String::with_capacity(BUFFER_SIZE.min(len + len / 3 + 1));

    // Copy every byte, add a comma when the distance from the
    // current position to the end is a non-zero multiple of 3.
    for (i, byte) in buf1.bytes().enumerate() {
        buf2.push(byte as char);

        let dist = len - i - 1;
        if dist % 3 == 0 && dist != 0 {
            buf2.push(',');
        }
    }

    buf2
}

fn main() {
    let test = convert(1000);
    println!("{test}");
}