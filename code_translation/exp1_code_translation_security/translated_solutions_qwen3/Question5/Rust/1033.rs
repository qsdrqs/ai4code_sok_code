use itoa;

/// Converts an integer to a comma-separated string and writes it into the provided buffer.
/// The buffer is null-terminated.
pub fn num_to_str(num: i32, buf: &mut [u8]) {
    if num == 0 {
        if let Some(b) = buf.first_mut() {
            *b = b'0';
        }
        if buf.len() > 1 {
            buf[1] = b'\0';
        }
        return;
    }

    let is_negative = num < 0;
    let abs_num = if is_negative {
        num.checked_abs().unwrap_or(i32::MAX)
    } else {
        num
    };

    // Convert the absolute number to a string
    let mut itoa_buf = itoa::Buffer::new();
    let s = itoa_buf.format(abs_num);
    let bytes = s.as_bytes();

    // Build the formatted string with commas
    let mut formatted = Vec::new();

    if is_negative {
        formatted.push(b'-');
    }

    // Reverse the digits and insert commas every 3 digits
    for (i, &b) in bytes.iter().rev().enumerate() {
        if i > 0 && i % 3 == 0 {
            formatted.push(b',');
        }
        formatted.push(b);
    }

    // Reverse the formatted string to get the correct order
    formatted.reverse();

    // Copy the formatted string into the buffer
    let mut idx = 0;
    for &b in &formatted {
        if idx < buf.len() {
            buf[idx] = b;
            idx += 1;
        } else {
            break;
        }
    }

    // Null-terminate the buffer
    if idx < buf.len() {
        buf[idx] = b'\0';
    }
}

/// Example usage
fn main() {
    let mut str_buf = [0u8; 24];
    num_to_str(7042, &mut str_buf);

    // Convert to a Rust string and print
    let c_str = unsafe { std::ffi::CStr::from_ptr(str_buf.as_ptr() as *const _) };
    if let Ok(rust_str) = c_str.to_str() {
        println!("Output: {}", rust_str);
    } else {
        println!("Invalid UTF-8 in buffer");
    }
}