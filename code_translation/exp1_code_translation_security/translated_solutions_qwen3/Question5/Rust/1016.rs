fn int_to_string(num: i32) -> String {
    let mut temp = num;
    let mut len = 0;
    let mut counter = 0;

    // Calculate the length of the number including commas
    while temp != 0 {
        temp /= 10;
        len += 1;
        counter += 1;
        if counter == 4 {
            len += 1;
            counter = 1;
        }
    }

    // Allocate buffer with len + 1 for the null terminator
    let mut buffer = vec![0u8; len + 1];

    let mut stop = 0;
    if num < 0 {
        // Extend buffer to accommodate the minus sign
        buffer.resize(len + 2, 0);
        buffer[0] = b'-';
        stop = 1;
    }

    let mut current_num = num.abs();
    let mut count = 0;

    // Fill the buffer from the end to the beginning
    for i in (stop..buffer.len() - 1).rev() {
        count += 1;
        if count == 4 {
            buffer[i] = b',';
            count = 0;
        } else {
            buffer[i] = b'0' + (current_num % 10) as u8;
            current_num /= 10;
        }
    }

    // Null-terminate the string
    buffer[buffer.len() - 1] = 0;

    // Convert to String, excluding the null terminator
    String::from_utf8_lossy(&buffer[..buffer.len() - 1]).into_owned()
}

fn main() {
    let num = -10099870;
    let s = int_to_string(num);
    println!("{}", s); // Expected output: -10,099,870
}