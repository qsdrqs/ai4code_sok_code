pub fn int2str(num: i32) -> String {
    let s = num.to_string();
    let length = s.len();

    let newl = if length % 3 == 0 {
        length + length / 3 - 1
    } else {
        length + length / 3
    };

    let mut result = vec![0; newl];
    let mut counter = 0;
    let mut j = newl as isize - 1;

    for c in s.chars().rev() {
        counter += 1;

        if j >= 0 {
            result[j as usize] = c as u8;
        }

        if counter % 3 == 0 {
            if j >= 0 {
                result[j as usize] = b',';
            }
            j -= 1;
        }

        j -= 1;
    }

    // Convert the byte buffer to a UTF-8 String
    String::from_utf8(result).expect("Invalid UTF-8 in result")
}