// Write a function in Rust that takes an integer and converts it to a string
// using the same grouping rule as the original C code:
// – 1 comma after the first 4 digits from the right,
// – then 1 comma after every 3 further digits.

fn int_to_string(num: i32) -> String {
    // Special-case 0 early (the C version is actually wrong for 0).
    if num == 0 {
        return "0".to_string();
    }

    // 1. Compute how many bytes we need (digits + commas [+ sign]).
    let mut len = 0;
    let mut temp = num.abs();          // Only count the magnitude’s digits.
    let mut counter = 0;               // Counts digits until we hit 4/3 rule.

    while temp != 0 {
        temp /= 10;
        len += 1;
        counter += 1;

        // There’s one comma after the first 4 digits,
        // then one after every 3 further digits.
        if counter == 4 {
            len += 1;      // space for comma
            counter = 1;   // reset to “we already have 1 digit” (4->1)
        }
    }

    if num < 0 {
        len += 1; // account for the minus sign
    }

    // 2. Fill the buffer from the back (exactly like the C code).
    let mut buf = vec![0u8; len as usize];
    let mut i: i32 = len as i32 - 1;
    let stop = if num < 0 { 1 } else { 0 };  // leave room for ‘-’ if needed
    let mut count = 0;
    let mut n = num.abs();                   // work with positive value

    while i >= stop {
        count += 1;
        if count == 4 {
            buf[i as usize] = b',';
            count = 0;
            i -= 1;
            continue;
        }

        buf[i as usize] = (n % 10) as u8 + b'0';
        n /= 10;
        i -= 1;
    }

    if num < 0 {
        buf[0] = b'-';
    }

    // 3. Convert the Vec<u8> into a real `String`.
    String::from_utf8(buf).expect("Generated ASCII bytes must be valid UTF-8")
}

fn main() {
    let num = -10099870;
    let s = int_to_string(num);
    println!("{}", s); // prints: -10,099,870
}