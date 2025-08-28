// main.rs

/// Return a `String` containing the decimal expansion of `value`
/// with commas every three digits, e.g.  -1_234_567  →  "-1,234,567".
fn string_it(value: i32) -> String {
    // Remember whether the number is negative.
    let negative = value < 0;

    // Work with an i64 so that `-i32::MIN` does not overflow.
    let mut abs_value = value as i64;
    if abs_value < 0 {
        abs_value = -abs_value;
    }

    // Decimal representation of the absolute value.
    let mut s = abs_value.to_string(); // e.g. "1234567"

    // Insert commas from the right every three digits.
    // We walk *backwards* through the string.
    let mut insert_pos: isize = s.len() as isize - 3;
    while insert_pos > 0 {
        s.insert(insert_pos as usize, ',');
        // After inserting a comma the string is one char longer,
        // so we only move three positions to the left.
        insert_pos -= 3;
    }

    // Re-attach the sign if the original value was negative.
    if negative {
        s.insert(0, '-');
    }

    s
}

fn main() {
    println!("   {}", string_it(-123_457));
}