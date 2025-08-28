pub fn num_to_string(num: i32) -> String {
    // Handle the special case of zero
    if num == 0 {
        return "0".to_string();
    }

    // Determine if the number is negative
    let is_neg = num < 0;
    let num = num.abs();

    // Count the number of digits in the number
    let mut total_digits = 0;
    let mut cur_num = num;
    while cur_num > 0 {
        cur_num /= 10;
        total_digits += 1;
    }

    // Calculate the number of commas needed
    let num_commas = (total_digits - 1) / 3;

    // Calculate the total length of the resulting string
    let mut str_len = total_digits + num_commas;
    if is_neg {
        str_len += 1; // for the '-' sign
    }

    // Initialize a vector to hold the characters of the result string
    let mut s = vec![' '; str_len];

    // Rebuild the number in the correct format
    cur_num = num;
    let mut index = 0;
    let mut digits = 0;

    while cur_num > 0 {
        let cur_digit = (cur_num % 10) as u8 + b'0';
        let cur_char = cur_digit as char;

        // Place the digit at the correct position in the string
        let pos = str_len - index - 1;
        s[pos] = cur_char;

        cur_num /= 10;
        index += 1;
        digits += 1;

        // Insert a comma after every 3 digits
        if digits % 3 == 0 {
            let pos = str_len - index - 1;
            s[pos] = ',';
            index += 1;
        }
    }

    // Add the negative sign if needed
    if is_neg {
        s[0] = '-';
    }

    // Convert the vector of characters into a String
    s.into_iter().collect()
}

fn main() {
    // Example usage
    print!("{}", num_to_string(-5305000));
}