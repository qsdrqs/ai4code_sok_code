// Nothing from the standard library has to be imported explicitly;
// everything we need is available by default.

const MAX_LEN: usize = 16;       // kept for parity with the C example

// Convert an i32 into a String, inserting commas every three digits.
// e.g.  1234567  ->  "1,234,567"
//       -99      ->  "-99"
//       0        ->  "0"
fn num_to_string(num: i32) -> String {
    // Special–case zero so we never enter the main loop.
    if num == 0 {
        return "0".to_string();
    }

    //---------------------------------------
    // Step 1: handle the sign and count digits
    //---------------------------------------
    let mut is_neg = false;
    let mut num_abs = num;
    if num < 0 {
        is_neg = true;
        num_abs = -num;          // safe because we already excluded zero
    }

    // Count how many decimal digits the absolute value has.
    let mut cur_num = num_abs;
    let mut total_digits = 0;
    while cur_num > 0 {
        cur_num /= 10;
        total_digits += 1;
    }

    // Number of commas to be inserted
    let num_commas = (total_digits - 1) / 3;

    // Total printable length (not including the final '\0' C would need)
    let mut str_len = total_digits + num_commas;
    if is_neg {
        str_len += 1;            // reserve room for a leading '-'
    }

    //---------------------------------------
    // Step 2: fill a buffer from the back
    //---------------------------------------
    // Create a mutable vector of placeholder characters of the desired length.
    let mut chars: Vec<char> = vec!['\0'; str_len];

    cur_num = num_abs;
    let mut index: usize = 0;    // how many positions we have already filled
    let mut digits: usize = 0;   // digits written since the last comma

    while cur_num > 0 {
        // Current least–significant digit turned into the matching ASCII char
        let cur_char = ((cur_num % 10) as u8 + b'0') as char;
        chars[str_len - index - 1] = cur_char;   // write from the right
        cur_num /= 10;
        index  += 1;
        digits += 1;

        // Insert a comma every three digits **if more digits remain**.
        if digits % 3 == 0 && cur_num > 0 {
            chars[str_len - index - 1] = ',';
            index += 1;
        }
    }

    // Add the minus sign if necessary.
    if is_neg {
        chars[0] = '-';
    }

    //---------------------------------------
    // Step 3: turn the character buffer into a real String
    //---------------------------------------
    chars.into_iter().collect()
}

fn main() {
    // Should print  "-5,305,000"
    println!("{}", num_to_string(-5_305_000));
}