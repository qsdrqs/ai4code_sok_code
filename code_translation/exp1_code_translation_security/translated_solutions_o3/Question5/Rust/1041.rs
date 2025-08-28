// ---------------------------------------------
// Rust version of the original C program
// ---------------------------------------------

fn main() {
    let num = 1_000_000u32;            // same test-value the C code used
    let formatted = add_commas(num);   // format with commas
    println!("{formatted}");
}

// -------------------------------------------------------------
// Reverse a mutable byte slice in-place (ASCII only, like the C)
// -------------------------------------------------------------
fn reverse_str(buf: &mut [u8]) {
    if buf.is_empty() {
        return;
    }
    let mut i = 0;
    let mut j = buf.len() - 1;
    while i < j {
        buf.swap(i, j);
        i += 1;
        j -= 1;
    }
}

// -------------------------------------------------------------
// Convert an unsigned integer to a comma-separated string
// -------------------------------------------------------------
fn add_commas(mut num: u32) -> String {
    // Special-case 0, otherwise the loop below would yield an empty vec
    if num == 0 {
        return "0".to_owned();
    }

    // We build the result backwards (exactly like the C version),
    // pushing ASCII bytes into a Vec<u8>.
    let mut bytes: Vec<u8> = Vec::new();
    let mut count = 0;

    while num != 0 {
        // Push least-significant digit as an ASCII byte
        let digit = (num % 10) as u8 + b'0';
        bytes.push(digit);
        num /= 10;
        count += 1;

        // After every three digits—provided there are more digits coming—
        // push a comma.
        if count == 3 && num != 0 {
            bytes.push(b',');
            count = 0;
        }
    }

    // Reverse in-place so the digits/commas are in forward order
    reverse_str(&mut bytes);

    // Convert Vec<u8> -> String (safe because we only inserted ASCII)
    String::from_utf8(bytes).expect("ASCII only")
}