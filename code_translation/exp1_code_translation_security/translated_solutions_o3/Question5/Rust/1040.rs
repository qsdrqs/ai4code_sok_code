// --------------------------------------------
//  A direct Rust translation of the C program
// --------------------------------------------

fn num_digits(mut n: i32) -> usize {
    // Handle 0 explicitly; otherwise count the divisions by 10
    if n == 0 {
        return 1;
    }

    let mut digits = 0;
    while n != 0 {
        n /= 10;
        digits += 1;
    }
    digits
}

fn int_to_string_with_commas(mut value: i32) -> String {
    // Track the sign and work with the absolute value
    let is_negative = value < 0;
    if is_negative {
        value = -value;
    }

    let digits = num_digits(value);
    let mut num_commas = digits / 3;
    if digits % 3 == 0 {
        num_commas -= 1;
    }

    // Original digits as a byte slice
    let source = value.to_string();
    let size = digits + num_commas + if is_negative { 1 } else { 0 };

    // Build the destination string from the back—exactly
    // what the C code did with the `dest` array.
    let mut dest: Vec<u8> = vec![b' '; size]; // pre-alloc
    let mut write_pos = size - 1;            // index we are filling
    let mut read_pos = source.len() - 1;     // index inside `source`
    let mut digit_count = 0usize;            // how many digits we copied so far

    // Copy digits and insert commas
    loop {
        dest[write_pos] = source.as_bytes()[read_pos];
        if write_pos == 0 { break; }          // we just filled the last slot
        write_pos -= 1;
        digit_count += 1;
        if digit_count % 3 == 0 && read_pos > 0 {
            dest[write_pos] = b',';
            if write_pos == 0 { break; }
            write_pos -= 1;
        }

        // Move to the next digit (if any left)
        if read_pos == 0 { break; }
        read_pos -= 1;
    }

    // Write the minus sign, if any space left
    if is_negative {
        dest[0] = b'-';
    }

    // Convert Vec<u8> into a real UTF-8 String
    String::from_utf8(dest).expect("constructed ASCII should be valid UTF-8")
}

fn main() {
    let formatted = int_to_string_with_commas(7_000_000);
    println!("{}", formatted); // prints: 7,000,000
}