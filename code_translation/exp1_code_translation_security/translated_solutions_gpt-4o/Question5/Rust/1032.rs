fn int_to_string(num: i32) -> String {
    num.to_string()
}

fn int_to_string_with_commas(num: i32) -> String {
    let str = int_to_string(num);
    let len = str.len();
    let comma_count = (len - 1) / 3;

    // Create a new String with enough capacity
    let mut new_str = String::with_capacity(len + comma_count);

    let mut j = 0;
    for (i, c) in str.chars().enumerate() {
        if i > 0 && (len - i) % 3 == 0 {
            new_str.push(',');
        }
        new_str.push(c);
        j += 1;
    }

    new_str
}

fn main() {
    let num = 7654321;
    let str_with_commas = int_to_string_with_commas(num);
    println!("{}", str_with_commas);
}