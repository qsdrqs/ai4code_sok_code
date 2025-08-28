// No external crates are required – everything is handled with the Rust
// standard library.

/// Converts an `i32` to its decimal string representation.
///
/// (The original C version only worked for non-negative values; here we
/// support the sign as well, because it costs us nothing in Rust.)
fn int_to_string(num: i32) -> String {
    num.to_string()
}

/// Converts an `i32` to a decimal string and inserts thousands–separating
/// commas (e.g. 7654321 → "7,654,321").
///
/// Again, negative numbers are handled automatically.
fn int_to_string_with_commas(num: i32) -> String {
    // First get the plain decimal representation.
    let plain = int_to_string(num);

    // Split off a possible leading ‘-’ so we don’t try to comma-separate it.
    let (sign, digits) = if plain.starts_with('-') {
        ('-', &plain[1..])
    } else {
        ('\0', &plain[..])
    };

    let len           = digits.len();
    let comma_count   = if len <= 3 { 0 } else { (len - 1) / 3 };
    let capacity_hint = len + comma_count + if sign == '-' { 1 } else { 0 };

    let mut out = String::with_capacity(capacity_hint);

    // Put the minus sign back if there was one.
    if sign == '-' {
        out.push(sign);
    }

    // Copy the digits, injecting a comma whenever exactly a multiple
    // of three characters remain after the one we just wrote.
    for (i, ch) in digits.chars().enumerate() {
        out.push(ch);

        // Characters still to come *after* the current character.
        let remaining = len - i - 1;
        if remaining > 0 && remaining % 3 == 0 {
            out.push(',');
        }
    }

    out
}

fn main() {
    let num = 7_654_321;
    let with_commas = int_to_string_with_commas(num);
    println!("{}", with_commas); // → 7,654,321
}