fn power(base: i32, exponent: i32) -> i32 {
    let mut result = 1;
    let mut exp = exponent;
    while exp > 0 {
        result *= base;
        exp -= 1;
    }
    result
}

pub fn number_to_string(num: i32) -> String {
    let mut str_num = String::new();
    let mut num_size = 0;
    let mut temp = num;

    if num < 0 {
        temp *= -1;
    }

    while temp > 0 {
        temp /= 10;
        num_size += 1;
    }

    let comma_count = (num_size - 1) / 3;

    if num < 0 {
        str_num.push('-');
    }

    let mut current_num = if num < 0 { -num } else { num };

    for j in (0..num_size).rev() {
        let exponent = power(10, j as i32);
        let digit = current_num / exponent;
        str_num.push((digit as u8 + b'0') as char);
        current_num %= exponent;

        if j != 0 && j % 3 == 0 {
            str_num.push(',');
        }
    }

    str_num
}

fn main() {
    let num = -7000000;
    let str_num = number_to_string(num);
    println!("{}", str_num);
}