// All dependencies are included in the standard library, so no external crates are needed.

// The MAX_LEN constant from the C code. For a 32-bit signed integer,
// the longest possible string with commas is "-2,147,483,647", which is 14 characters.
const MAX_LEN: usize = 14;

/// Translates the C function `stringify`.
///
/// This function converts an integer into a string with comma separators for thousands,
/// storing the result in the provided byte buffer `buf`. It returns the length of the
/// resulting string.
///
/// NOTE: The original C code contains several bugs that have been corrected in this
/// Rust version to reflect the likely *intent* of the original author:
/// 1.  **Handling of 0:** The C code fails for an input of 0. This version correctly
///     handles it, producing the string "0".
/// 2.  **Integer Overflow:** The C code calculates the absolute value via `i * -1`,
///     which causes an overflow for `i32::MIN`. This version uses `unsigned_abs()`
///     to avoid this bug.
/// 3.  **Comma Logic:** The C code's comma-insertion logic is flawed and overwrites
///     digits. This version implements a standard, correct algorithm for
///     inserting thousands separators.
/// 4.  **Buffer Copying:** The C code has an off-by-one error in its final loop,
///     leading to an out-of-bounds read. This version uses safe slice copying.
///
/// The core algorithm of building the string in reverse in a temporary buffer is preserved.
fn stringify(i: i32, buf: &mut [u8]) -> usize {
    // 1. Handle the edge case of 0, which the original C code fails to do.
    if i == 0 {
        if !buf.is_empty() {
            buf[0] = b'0';
        }
        return 1;
    }

    // A temporary buffer to build the string in reverse, similar to the C version.
    let mut tmp = [0u8; MAX_LEN];
    
    let is_negative = i < 0;
    // 2. Use `unsigned_abs()` to correctly handle i32::MIN.
    let mut j = i.unsigned_abs();
    
    // `k` is the index pointing to the next position to write in `tmp`, starting from the end.
    let mut k = MAX_LEN;
    // `c` counts the number of digits written to determine where to place commas.
    let mut c = 0;

    // Loop until all digits of the number are processed.
    while j > 0 {
        // Decrement k to move to the next character position (from right to left).
        // We check against 0 to prevent panic, making the code robust.
        if k == 0 { break; }
        k -= 1;

        // 3. This is the corrected comma insertion logic.
        // A comma is inserted after every 3 digits, but not at the very beginning.
        if c > 0 && c % 3 == 0 {
            tmp[k] = b',';
            // Move to the next position for the digit.
            if k == 0 { break; }
            k -= 1;
        }
        
        // Convert the last digit of `j` to a character and place it in the buffer.
        let digit = (j % 10) as u8;
        tmp[k] = digit + b'0';
        
        // Move to the next digit.
        j /= 10;
        c += 1;
    }
    
    // If the original number was negative, add the '-' sign at the beginning.
    if is_negative {
        if k > 0 {
            k -= 1;
            tmp[k] = b'-';
        }
    }
    
    // The final string is the slice of `tmp` from `k` to the end.
    let result_slice = &tmp[k..];
    let len = result_slice.len();
    
    // 4. Safely copy the generated string into the output buffer.
    // We only copy as much as the buffer can hold to prevent panics.
    let copy_len = std::cmp::min(len, buf.len());
    buf[..copy_len].copy_from_slice(&result_slice[..copy_len]);
    
    // The function returns the total length of the generated string,
    // which might be larger than the provided buffer.
    len
}


fn main() {
  // Create a buffer to hold the string. It's initialized to zeros.
  // The size matches the C code.
  let mut buf = [0u8; MAX_LEN];
  
  // The input value from the C code's main function.
  let input = -2147483647;
  
  // Call the stringify function.
  let l = stringify(input, &mut buf);
  
  // Convert the resulting byte slice in the buffer to a &str for printing.
  // We only use the part of the buffer that was written to (up to length `l`).
  // We use `unwrap()` because we know `stringify` produces valid UTF-8 characters.
  let s = std::str::from_utf8(&buf[..l]).unwrap();
  
  // Print the length and the string, mimicking the C `printf` statement.
  println!("{} :: {}", l, s); // Expected output: 14 :: -2,147,483,647
}